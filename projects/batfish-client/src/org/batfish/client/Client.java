package org.batfish.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.output.WriterOutputStream;
import org.batfish.client.Settings.RunMode;
import org.batfish.common.BfConsts;
import org.batfish.common.BatfishLogger;
import org.batfish.common.WorkItem;
import org.batfish.common.CoordConsts.WorkStatusCode;
import org.batfish.common.util.BatfishObjectMapper;
import org.batfish.common.util.CommonUtil;
import org.batfish.common.util.ZipUtility;
import org.batfish.datamodel.answers.Answer;
import org.batfish.datamodel.questions.QuestionType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;

public class Client {

   private static final String COMMAND_ANSWER = "answer";
   private static final String COMMAND_ANSWER_DIFF = "answer-diff";
   private static final String COMMAND_CAT = "cat";
   private static final String COMMAND_CHECK_API_KEY = "checkapikey";
   private static final String COMMAND_CLEAR_SCREEN = "cls";
   private static final String COMMAND_DEL_CONTAINER = "del-container";
   private static final String COMMAND_DEL_ENVIRONMENT = "del-environment";
   private static final String COMMAND_DEL_QUESTION = "del-question";
   private static final String COMMAND_DEL_TESTRIG = "del-testrig";
   private static final String COMMAND_DIR = "dir";
   private static final String COMMAND_ECHO = "echo";
   private static final String COMMAND_EXIT = "exit";
   private static final String COMMAND_GEN_DIFF_DP = "generate-diff-dataplane";
   private static final String COMMAND_GEN_DP = "generate-dataplane";
   private static final String COMMAND_GET = "get";
   private static final String COMMAND_GET_DIFF = "get-diff";
   private static final String COMMAND_HELP = "help";
   private static final String COMMAND_INIT_CONTAINER = "init-container";
   private static final String COMMAND_INIT_DIFF_ENV = "init-diff-environment";
   private static final String COMMAND_INIT_DIFF_TESTRIG = "init-diff-testrig";
   private static final String COMMAND_INIT_TESTRIG = "init-testrig";
   private static final String COMMAND_LIST_CONTAINERS = "list-containers";
   private static final String COMMAND_LIST_ENVIRONMENTS = "list-environments";
   private static final String COMMAND_LIST_QUESTIONS = "list-questions";
   private static final String COMMAND_LIST_TESTRIGS = "list-testrigs";
   private static final String COMMAND_PROMPT = "prompt";
   private static final String COMMAND_PWD = "pwd";
   private static final String COMMAND_QUIT = "quit";
   private static final String COMMAND_SET_BATFISH_LOGLEVEL = "set-batfish-loglevel";
   private static final String COMMAND_SET_CONTAINER = "set-container";
   private static final String COMMAND_SET_DIFF_ENV = "set-diff-environment";
   private static final String COMMAND_SET_DIFF_TESTRIG = "set-diff-testrig";
   private static final String COMMAND_SET_ENV = "set-environment";
   private static final String COMMAND_SET_LOGLEVEL = "set-loglevel";
   private static final String COMMAND_SET_TESTRIG = "set-testrig";
   private static final String COMMAND_SHOW_API_KEY = "show-api-key";
   private static final String COMMAND_SHOW_BATFISH_LOGLEVEL = "show-batfish-loglevel";
   private static final String COMMAND_SHOW_CONTAINER = "show-container";
   private static final String COMMAND_SHOW_COORDINATOR_HOST = "show-coordinator-host";
   private static final String COMMAND_SHOW_LOGLEVEL = "show-loglevel";
   private static final String COMMAND_SHOW_DIFF_TESTRIG = "show-diff-testrig";
   private static final String COMMAND_SHOW_TESTRIG = "show-testrig";
   private static final String COMMAND_TEST = "test";
   private static final String COMMAND_UPLOAD_CUSTOM_OBJECT = "upload-custom";

   private static final String DEFAULT_CONTAINER_PREFIX = "cp";
   private static final String DEFAULT_DIFF_ENV_PREFIX = "env_";
   private static final String DEFAULT_ENV_NAME = BfConsts.RELPATH_DEFAULT_ENVIRONMENT_NAME;
   private static final String DEFAULT_QUESTION_PREFIX = "q";
   private static final String DEFAULT_TESTRIG_PREFIX = "tr_";

   private static final Map<String, String> MAP_COMMANDS = initCommands();

   private static Map<String, String> initCommands() {
      Map<String, String> descs = new TreeMap<String, String>();
      descs.put(COMMAND_ANSWER, COMMAND_ANSWER
            + " <question-file> [param1=value1 [param2=value2] ...]\n"
            + "\t Answer the question in the file for the default environment");
      descs.put(
            COMMAND_ANSWER_DIFF,
            COMMAND_ANSWER_DIFF
                  + " <question-file>  [param1=value1 [param2=value2] ...]\n"
                  + "\t Answer the question in the file for the differential environment");
      descs.put(COMMAND_CAT, COMMAND_CAT + " <filename>\n"
            + "\t Print the contents of the file");
      // descs.put(COMMAND_CHANGE_DIR, COMMAND_CHANGE_DIR
      // + " <dirname>\n"
      // + "\t Change the working directory");
      descs.put(COMMAND_CLEAR_SCREEN, COMMAND_CLEAR_SCREEN + "\n"
            + "\t Clear screen");
      descs.put(COMMAND_DEL_CONTAINER, COMMAND_DEL_CONTAINER
            + "<container-name>" + "\t Delete the specified container");
      descs.put(COMMAND_DEL_ENVIRONMENT, COMMAND_DEL_ENVIRONMENT
            + "<environment-name>" + "\t Delete the specified environment");
      descs.put(COMMAND_DEL_QUESTION, COMMAND_DEL_QUESTION + "<question-name>"
            + "\t Delete the specified question");
      descs.put(COMMAND_DEL_TESTRIG, COMMAND_DEL_TESTRIG + "<testrig-name>"
            + "\t Delete the specified testrig");
      descs.put(COMMAND_DIR, COMMAND_DIR + "<dir>"
            + "\t List directory contents");
      descs.put(COMMAND_ECHO, COMMAND_ECHO + "<message>"
            + "\t Echo the message");
      descs.put(COMMAND_EXIT, COMMAND_EXIT + "\n"
            + "\t Terminate interactive client session");
      descs.put(COMMAND_GEN_DIFF_DP, COMMAND_GEN_DIFF_DP + "\n"
            + "\t Generate dataplane for the differential environment");
      descs.put(COMMAND_GEN_DP, COMMAND_GEN_DP + "\n"
            + "\t Generate dataplane for the default environment");
      descs.put(COMMAND_GET, COMMAND_GET
            + " <question-type>  [param1=value1 [param2=value2] ...]\n"
            + "\t Answer the question by type for the differential environment");
      descs.put(COMMAND_GET_DIFF, COMMAND_GET_DIFF
            + " <question-file>  [param1=value1 [param2=value2] ...]\n"
            + "\t Answer the question by type for the differential environment");
      descs.put(COMMAND_HELP, COMMAND_HELP + "\n"
            + "\t Print the list of supported commands");
      descs.put(COMMAND_CHECK_API_KEY, COMMAND_CHECK_API_KEY
            + "\t Check if API Key is valid");
      descs.put(COMMAND_INIT_CONTAINER, COMMAND_INIT_CONTAINER
            + " [<container-name-prefix>]\n" + "\t Initialize a new container");
      descs.put(
            COMMAND_INIT_DIFF_ENV,
            COMMAND_INIT_DIFF_ENV
                  + " [-nodataplane] <environment zipfile or directory> [<environment-name>]\n"
                  + "\t Initialize the differential environment");
      descs.put(
            COMMAND_INIT_DIFF_TESTRIG,
            COMMAND_INIT_DIFF_TESTRIG
                  + " [-nodataplane] <testrig zipfile or directory> [<environment name>]\n"
                  + "\t Initialize the diff testrig with default environment");
      descs.put(
            COMMAND_INIT_TESTRIG,
            COMMAND_INIT_TESTRIG
                  + " [-nodataplane] <testrig zipfile or directory> [<environment name>]\n"
                  + "\t Initialize the testrig with default environment");
      descs.put(COMMAND_LIST_CONTAINERS, COMMAND_LIST_CONTAINERS + "\n"
            + "\t List the containers to which you have access");
      descs.put(COMMAND_LIST_ENVIRONMENTS, COMMAND_LIST_ENVIRONMENTS + "\n"
            + "\t List the environments under current container and testrig");
      descs.put(COMMAND_LIST_QUESTIONS, COMMAND_LIST_QUESTIONS + "\n"
            + "\t List the questions under current container and testrig");
      descs.put(COMMAND_LIST_TESTRIGS, COMMAND_LIST_TESTRIGS + "\n"
            + "\t List the testrigs within the current container");
      descs.put(COMMAND_PROMPT, COMMAND_PROMPT + "\n"
            + "\t Prompts for user to press enter");
      descs.put(COMMAND_PWD, COMMAND_PWD + "\n"
            + "\t Prints the working directory");
      descs.put(COMMAND_QUIT, COMMAND_QUIT + "\n"
            + "\t Terminate interactive client session");
      descs.put(COMMAND_SET_BATFISH_LOGLEVEL, COMMAND_SET_BATFISH_LOGLEVEL
            + " <debug|info|output|warn|error>\n"
            + "\t Set the batfish loglevel. Default is warn");
      descs.put(COMMAND_SET_CONTAINER, COMMAND_SET_CONTAINER
            + " <container-name>\n" + "\t Set the current container");
      descs.put(COMMAND_SET_DIFF_ENV, COMMAND_SET_DIFF_ENV
            + " <environment-name>\n" + "\t Set the differential environment");
      descs.put(COMMAND_SET_DIFF_TESTRIG, COMMAND_SET_DIFF_TESTRIG
            + " <testrig-name> [environment name]\n"
            + "\t Set the differential testrig");
      descs.put(COMMAND_SET_ENV, COMMAND_SET_ENV + " <environment-name>\n"
            + "\t Set the current base environment");
      descs.put(COMMAND_SET_LOGLEVEL, COMMAND_SET_LOGLEVEL
            + " <debug|info|output|warn|error>\n"
            + "\t Set the client loglevel. Default is output");
      descs.put(COMMAND_SET_TESTRIG, COMMAND_SET_TESTRIG
            + " <testrig-name> [environment name]\n"
            + "\t Set the base testrig");
      descs.put(COMMAND_SHOW_API_KEY, COMMAND_SHOW_API_KEY + "\n"
            + "\t Show API Key");
      descs.put(COMMAND_SHOW_BATFISH_LOGLEVEL, COMMAND_SHOW_BATFISH_LOGLEVEL
            + "\n" + "\t Show current batfish loglevel");
      descs.put(COMMAND_SHOW_CONTAINER, COMMAND_SHOW_CONTAINER + "\n"
            + "\t Show active container");
      descs.put(COMMAND_SHOW_COORDINATOR_HOST, COMMAND_SHOW_COORDINATOR_HOST
            + "\n" + "\t Show coordinator host");
      descs.put(COMMAND_SHOW_LOGLEVEL, COMMAND_SHOW_LOGLEVEL + "\n"
            + "\t Show current client loglevel");
      descs.put(COMMAND_SHOW_DIFF_TESTRIG, COMMAND_SHOW_DIFF_TESTRIG + "\n"
            + "\t Show differential testrig and environment");
      descs.put(COMMAND_SHOW_TESTRIG, COMMAND_SHOW_TESTRIG + "\n"
            + "\t Show base testrig and environment");
      descs.put(COMMAND_TEST, COMMAND_TEST 
            + " <reference file> <command> \n" 
            + "\t Show base testrig and environment");
      descs.put(COMMAND_UPLOAD_CUSTOM_OBJECT, COMMAND_UPLOAD_CUSTOM_OBJECT
            + " <object-name> <object-file>\n" + "\t Uploads a custom object");
      return descs;
   }

   private String _currContainerName = null;
   private String _currDiffEnv = null;
   private String _currDiffTestrig;
   private String _currEnv = null;
   private String _currTestrig = null;

   private BatfishLogger _logger;
   private BfCoordPoolHelper _poolHelper;
   private ConsoleReader _reader;

   private Settings _settings;

   private BfCoordWorkHelper _workHelper;

   public Client(Settings settings) {
      _settings = settings;

      switch (_settings.getRunMode()) {
      case batch:
         if (_settings.getBatchCommandFile() == null) {
            System.err
                  .println("org.batfish.client: Command file not specified while running in batch mode.");
            System.err
                  .printf(
                        "Use '-%s <cmdfile>' if you want batch mode, or '-%s interactive' if you want interactive mode\n",
                        Settings.ARG_COMMAND_FILE, Settings.ARG_RUN_MODE);
            System.exit(1);
         }
         _logger = new BatfishLogger(_settings.getLogLevel(), false,
               _settings.getLogFile(), false, false);
         break;
      case genquestions:
         _logger = new BatfishLogger(_settings.getLogLevel(), false,
               _settings.getLogFile(), false, false);
         break;
      case interactive:
         try {
            _reader = new ConsoleReader();
            _reader.setPrompt("batfish> ");

            List<Completer> completors = new LinkedList<Completer>();
            completors.add(new StringsCompleter(MAP_COMMANDS.keySet()));

            for (Completer c : completors) {
               _reader.addCompleter(c);
            }

            PrintWriter pWriter = new PrintWriter(_reader.getOutput(), true);
            OutputStream os = new WriterOutputStream(pWriter);
            PrintStream ps = new PrintStream(os, true);
            _logger = new BatfishLogger(_settings.getLogLevel(), false, ps);
         }
         catch (Exception e) {
            System.err.printf("Could not initialize client: %s\n",
                  e.getMessage());
            System.exit(1);
         }
         break;
      default:
         System.err.println("org.batfish.client: Unknown run mode.");
         System.exit(1);
      }

   }

   public Client(String[] args) throws Exception {
      this(new Settings(args));
   }

   private boolean answerFile(String questionFile, String paramsLine,
         boolean isDiff, FileWriter outWriter) throws Exception {

      if (!new File(questionFile).exists()) {
         throw new FileNotFoundException("Question file not found: "
               + questionFile);
      }

      String questionName = DEFAULT_QUESTION_PREFIX + "_"
            + UUID.randomUUID().toString();

      File paramsFile = createTempFile("parameters", paramsLine);
      paramsFile.deleteOnExit();

      // upload the question
      boolean resultUpload = _workHelper.uploadQuestion(_currContainerName,
            _currTestrig, questionName, questionFile,
            paramsFile.getAbsolutePath());

      if (!resultUpload) {
         return false;
      }

      _logger.debug("Uploaded question. Answering now.\n");

      // delete the temporary params file
      if (paramsFile != null) {
         paramsFile.delete();
      }

      // answer the question
      WorkItem wItemAs = (isDiff) ? _workHelper.getWorkItemAnswerDiffQuestion(
            questionName, _currContainerName, _currTestrig, _currEnv,
            _currDiffTestrig, _currDiffEnv) : _workHelper
            .getWorkItemAnswerQuestion(questionName, _currContainerName,
                  _currTestrig, _currEnv, _currDiffTestrig, _currDiffEnv);
      return execute(wItemAs, outWriter);
   }

   private boolean answerType(String questionType, String paramsLine,
         boolean isDiff, FileWriter outWriter) throws Exception {

      Map<String, String> parameters = parseParams(paramsLine);

      String questionString = QuestionHelper.getQuestionString(questionType,
            isDiff);
      _logger.debugf("Question Json:\n%s\n", questionString);

      String parametersString = QuestionHelper.getParametersString(parameters);
      _logger.debugf("Parameters Json:\n%s\n", parametersString);

      File questionFile = createTempFile("question", questionString);

      boolean result = answerFile(questionFile.getAbsolutePath(),
            parametersString, isDiff, outWriter);

      if (questionFile != null) {
         questionFile.delete();
      }

      return result;
   }

   private File createTempFile(String filePrefix, String content)
         throws IOException {

      File tempFile = Files.createTempFile(filePrefix, null).toFile();
      tempFile.deleteOnExit();

      _logger.debugf("Creating temporary %s file: %s\n", filePrefix,
            tempFile.getAbsolutePath());

      FileWriter writer = new FileWriter(tempFile);
      writer.write(content + "\n");
      writer.close();

      return tempFile;
   }

   private boolean execute(WorkItem wItem, FileWriter outWriter) throws Exception {

      wItem.addRequestParam(BfConsts.ARG_LOG_LEVEL,
            _settings.getBatfishLogLevel());
      _logger.info("work-id is " + wItem.getId() + "\n");

      boolean queueWorkResult = _workHelper.queueWork(wItem);
      _logger.info("Queuing result: " + queueWorkResult + "\n");

      if (!queueWorkResult) {
         return queueWorkResult;
      }

      WorkStatusCode status = _workHelper.getWorkStatus(wItem.getId());

      while (status != WorkStatusCode.TERMINATEDABNORMALLY
            && status != WorkStatusCode.TERMINATEDNORMALLY
            && status != WorkStatusCode.ASSIGNMENTERROR) {

         _logger.output(". ");
         _logger.infof("status: %s\n", status);

         Thread.sleep(1 * 1000);

         status = _workHelper.getWorkStatus(wItem.getId());
      }

      _logger.output("\n");
      _logger.infof("final status: %s\n", status);

      // get the answer
      String ansFileName = wItem.getId() + BfConsts.SUFFIX_ANSWER_JSON_FILE;
      String downloadedAnsFile = _workHelper.getObject(
            wItem.getContainerName(), wItem.getTestrigName(), ansFileName);

      if (downloadedAnsFile == null) {
         _logger
               .errorf(
                     "Failed to get answer file %s. Fix batfish and remove the statement below this line\n",
                     ansFileName);
         // return false;
      }
      else {
         String answerString = CommonUtil
               .readFile(Paths.get(downloadedAnsFile));
         
         if (outWriter == null) {
            _logger.output(answerString + "\n");
         }
         else {
            outWriter.write(answerString);
         }

         //the code below tests serialization/deserialization
         try {
            ObjectMapper mapper = new BatfishObjectMapper();
            Answer answer = mapper.readValue(answerString, Answer.class);

            // printf debugging
            // org.batfish.datamodel.answers.CompareSameNameAnswerElement
            // csnAnswer =
            // (org.batfish.datamodel.answers.CompareSameNameAnswerElement)
            // answer.getAnswerElements().get(0);
            // _logger.outputf("answer: %s",
            // csnAnswer.getEquivalenceSets().get("RouteFilterList").get_sameNamedStructures().size());

            String newAnswerString = mapper.writeValueAsString(answer);
            JsonNode tree = mapper.readTree(answerString);
            JsonNode newTree = mapper.readTree(newAnswerString);
            if (!CommonUtil.checkJsonEqual(tree, newTree)) {
               // if (!tree.equals(newTree)) {
               _logger
                     .errorf(
                           "Original and recovered Json are different. Recovered = %s\n",
                           newAnswerString);
            }
         }
         catch (Exception e) {
            _logger.outputf("Could NOT deserialize Json to Answer: %s\n",
                  e.getMessage());
         }
      }

      // get the log
      _logger.output("---------------- Service Log --------------\n");
      String logFileName = wItem.getId() + BfConsts.SUFFIX_LOG_FILE;
      String downloadedFile = _workHelper.getObject(wItem.getContainerName(),
            wItem.getTestrigName(), logFileName);

      if (downloadedFile == null) {
         _logger.errorf("Failed to get log file %s\n", logFileName);
         return false;
      }
      else {
         try (BufferedReader br = new BufferedReader(new FileReader(
               downloadedFile))) {
            String line = null;
            while ((line = br.readLine()) != null) {
               _logger.output(line + "\n");
            }
         }
      }

      // TODO: remove the log file?

      if (status == WorkStatusCode.TERMINATEDNORMALLY) {
         return true;
      }
      else {
         // _logger.errorf("WorkItem failed: %s", wItem);
         return false;
      }
   }

   private boolean generateDataplane(FileWriter outWriter) throws Exception {
      if (!isSetTestrig() || !isSetContainer(true)) {
         return false;
      }

      // generate the data plane
      WorkItem wItemGenDp = _workHelper.getWorkItemGenerateDataPlane(
            _currContainerName, _currTestrig, _currEnv);

      return execute(wItemGenDp, outWriter);
   }

   private boolean generateDiffDataplane(FileWriter outWriter) throws Exception {
      if (!isSetDiffEnvironment() || !isSetTestrig() || !isSetContainer(true)) {
         return false;
      }

      WorkItem wItemGenDdp = _workHelper.getWorkItemGenerateDiffDataPlane(
            _currContainerName, _currTestrig, _currEnv, _currDiffEnv);

      return execute(wItemGenDdp, outWriter);
   }

   private void generateQuestions() {

      File questionsDir = Paths.get(_settings.getQuestionsDir()).toFile();

      if (!questionsDir.exists()) {
         if (!questionsDir.mkdirs()) {
            _logger.errorf("Could not create questions dir %s\n",
                  _settings.getQuestionsDir());
            System.exit(1);
         }
      }

      for (QuestionType qType : QuestionType.values()) {
         try {
            String questionString = QuestionHelper.getQuestionString(qType);

            String qFile = Paths
                  .get(_settings.getQuestionsDir(),
                        qType.questionTypeName() + ".json").toFile()
                  .getAbsolutePath();

            PrintWriter writer = new PrintWriter(qFile);
            writer.write(questionString);
            writer.close();
         }
         catch (Exception e) {
            _logger.errorf("Could not write question %s: %s\n",
                  qType.questionTypeName(), e.getMessage());
         }
      }
   }

   private List<String> getCommandOptions(String[] words) {
      List<String> options = new LinkedList<String>();

      int currIndex = 1;

      while (currIndex < words.length && words[currIndex].startsWith("-")) {
         options.add(words[currIndex]);
         currIndex++;
      }

      return options;
   }

   private List<String> getCommandParameters(String[] words, int numOptions) {
      List<String> parameters = new LinkedList<String>();

      for (int index = numOptions + 1; index < words.length; index++) {
         parameters.add(words[index]);
      }

      return parameters;
   }

   public BatfishLogger getLogger() {
      return _logger;
   }

   private void initHelpers() {

      String workMgr = _settings.getCoordinatorHost() + ":"
            + _settings.getCoordinatorWorkPort();
      String poolMgr = _settings.getCoordinatorHost() + ":"
            + _settings.getCoordinatorPoolPort();

      _workHelper = new BfCoordWorkHelper(workMgr, _logger, _settings);
      _poolHelper = new BfCoordPoolHelper(poolMgr);

      int numTries = 0;

      while (true) {
         try {
            numTries++;
            if (_workHelper.isReachable()) {
               // print this message only we might have printed unable to
               // connect message earlier
               if (numTries > 1) {
                  _logger.outputf("Connected to coordinator after %d tries\n",
                        numTries);
               }
               break;
            }
            Thread.sleep(1 * 1000); // 1 second
         }
         catch (Exception e) {
            _logger.errorf(
                  "Exeption while checking reachability to coordinator: ",
                  e.getMessage());
            System.exit(1);
         }
      }
   }

   private boolean isSetContainer(boolean printError) {
      if (!_settings.getSanityCheck()) {
         return true;
      }

      if (_currContainerName == null) {
         if (printError) {
            _logger.errorf("Active container is not set\n");
         }
         return false;
      }

      return true;
   }

   private boolean isSetDiffEnvironment() {
      if (!_settings.getSanityCheck()) {
         return true;
      }

      if (_currDiffTestrig == null) {
         _logger.errorf("Active diff testrig is not set\n");
         return false;
      }

      if (_currDiffEnv == null) {
         _logger.errorf("Active diff environment is not set\n");
         return false;
      }
      return true;
   }

   private boolean isSetTestrig() {
      if (!_settings.getSanityCheck()) {
         return true;
      }

      if (_currTestrig == null) {
         _logger.errorf("Active testrig is not set.\n");
         _logger
               .errorf(
                     "Specify testrig on command line (-%s <testrigdir>) or use command (%s [-nodataplane] <testrigdir>)\n",
                     Settings.ARG_TESTRIG_DIR, COMMAND_INIT_TESTRIG);
         return false;
      }
      return true;
   }

   private Map<String, String> parseParams(String paramsLine) {
      Map<String, String> parameters = new HashMap<String, String>();

      Pattern pattern = Pattern.compile("([\\w_]+)\\s*=\\s*(.+)");

      String[] params = paramsLine.split("\\|");

      _logger.debugf("Found %d parameters\n", params.length);

      for (String param : params) {
         Matcher matcher = pattern.matcher(param);

         while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();
            _logger.debugf("key=%s value=%s\n", key, value);

            parameters.put(key, value);
         }
      }

      return parameters;
   }

   private void printUsage() {
      for (Map.Entry<String, String> entry : MAP_COMMANDS.entrySet()) {
         _logger.output(entry.getValue() + "\n\n");
      }
   }

   private boolean processCommand(String command) {
      String line = command.trim();
      if (line.length() == 0 || line.startsWith("#")) {
         return true;
      }
      _logger.debug("Doing command: " + line + "\n");

      String[] words = line.split("\\s+");

      if (!validCommandUsage(words)) {
         return false;
      }

      return processCommand(words, null);
   }

   private boolean processCommand(String[] words, FileWriter outWriter) {
      try {
         List<String> options = getCommandOptions(words);
         List<String> parameters = getCommandParameters(words, options.size());

         String command = words[0];

         switch (command) {
         // this is a hidden command for testing
         case "add-worker": {
            boolean result = _poolHelper.addBatfishWorker(words[1]);
            _logger.output("Result: " + result + "\n");
            return true;
         }
         case COMMAND_ANSWER: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }

            String questionFile = parameters.get(0);
            String paramsLine = CommonUtil.joinStrings(" ",
                  Arrays.copyOfRange(words, 2 + options.size(), words.length));

            return answerFile(questionFile, paramsLine, false, outWriter);
         }
         case COMMAND_ANSWER_DIFF: {
            if (!isSetDiffEnvironment() || !isSetTestrig()
                  || !isSetContainer(true)) {
               return false;
            }

            String questionFile = parameters.get(0);
            String paramsLine = CommonUtil.joinStrings(" ",
                  Arrays.copyOfRange(words, 2 + options.size(), words.length));

            return answerFile(questionFile, paramsLine, true, outWriter);
         }
         case COMMAND_CAT: {
            String filename = words[1];

            try (BufferedReader br = new BufferedReader(
                  new FileReader(filename))) {
               String line = null;
               while ((line = br.readLine()) != null) {
                  _logger.output(line + "\n");
               }
            }

            return true;
         }
         case COMMAND_DEL_CONTAINER: {
            String containerName = parameters.get(0);
            boolean result = _workHelper.delContainer(containerName);
            _logger.outputf("Result of deleting container: %s\n", result);
            return true;
         }
         case COMMAND_DEL_ENVIRONMENT: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }

            String envName = parameters.get(0);
            boolean result = _workHelper.delEnvironment(_currContainerName,
                  _currTestrig, envName);
            _logger.outputf("Result of deleting environment: %s\n", result);
            return true;
         }
         case COMMAND_DEL_QUESTION: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }

            String qName = parameters.get(0);
            boolean result = _workHelper.delQuestion(_currContainerName,
                  _currTestrig, qName);
            _logger.outputf("Result of deleting question: %s\n", result);
            return true;
         }
         case COMMAND_DEL_TESTRIG: {
            if (!isSetContainer(true)) {
               return false;
            }

            String testrigName = parameters.get(0);
            boolean result = _workHelper.delTestrig(_currContainerName,
                  testrigName);
            _logger.outputf("Result of deleting testrig: %s\n", result);
            return true;
         }
         case COMMAND_DIR: {
            String dirname = (parameters.size() == 1) ? parameters.get(0) : ".";

            File currDirectory = new File(dirname);
            for (File file : currDirectory.listFiles()) {
               _logger.output(file.getName() + "\n");
            }
            return true;
         }
         case COMMAND_ECHO: {
            _logger.outputf(
                  "%s\n",
                  CommonUtil.joinStrings(" ",
                        Arrays.copyOfRange(words, 1, words.length)));
            return true;
         }
         case COMMAND_EXIT:
         case COMMAND_QUIT: {
            System.exit(0);
            return true;
         }
         case COMMAND_GEN_DP: {
            return generateDataplane(outWriter);
         }
         case COMMAND_GEN_DIFF_DP: {
            return generateDiffDataplane(outWriter);
         }
         case COMMAND_GET: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }

            String questionType = parameters.get(0);
            String paramsLine = CommonUtil.joinStrings(" ",
                  Arrays.copyOfRange(words, 2 + options.size(), words.length));

            return answerType(questionType, paramsLine, false, outWriter);
         }
         case COMMAND_GET_DIFF: {
            if (!isSetDiffEnvironment() || !isSetTestrig()
                  || !isSetContainer(true)) {
               return false;
            }

            String questionType = parameters.get(0);
            String paramsLine = CommonUtil.joinStrings(" ",
                  Arrays.copyOfRange(words, 2 + options.size(), words.length));

            return answerType(questionType, paramsLine, true, outWriter);
         }
         case COMMAND_HELP: {
            printUsage();
            return true;
         }
         case COMMAND_CHECK_API_KEY: {
            String isValid = _workHelper.checkApiKey();
            _logger.outputf("Api key validitiy: %s\n", isValid);
            return true;
         }
         case COMMAND_INIT_CONTAINER: {
            String containerPrefix = (words.length > 1) ? words[1]
                  : DEFAULT_CONTAINER_PREFIX;
            _currContainerName = _workHelper.initContainer(containerPrefix);
            _logger.outputf("Active container set to %s\n", _currContainerName);
            return true;
         }
         case COMMAND_INIT_DIFF_ENV: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }

            // check if we are being asked to not generate the dataplane
            boolean generateDiffDataplane = true;

            if (options.size() == 1) {
               if (options.get(0).equals("-nodataplane")) {
                  generateDiffDataplane = false;
               }
               else {
                  _logger.outputf("Unknown option %s\n", options.get(0));
                  return false;
               }
            }

            String diffEnvLocation = parameters.get(0);
            String diffEnvName = (parameters.size() > 1) ? parameters.get(1)
                  : DEFAULT_DIFF_ENV_PREFIX + UUID.randomUUID().toString();

            if (!uploadTestrigOrEnv(diffEnvLocation, diffEnvName, false)) {
               return false;
            }

            _currDiffEnv = diffEnvName;
            _currDiffTestrig = _currTestrig;

            _logger.outputf("Active diff testrig->environment is now %s->%s\n",
                  _currDiffTestrig, _currDiffEnv);

            WorkItem wItemGenDdp = _workHelper
                  .getWorkItemCompileDiffEnvironment(_currContainerName,
                        _currDiffTestrig, _currEnv, _currDiffEnv);

            if (!execute(wItemGenDdp, outWriter)) {
               return false;
            }

            if (generateDiffDataplane) {
               _logger.output("Generating delta dataplane\n");

               if (!generateDiffDataplane(outWriter)) {
                  return false;
               }

               _logger.output("Generated delta dataplane\n");
            }

            return true;
         }
         case COMMAND_INIT_DIFF_TESTRIG:
         case COMMAND_INIT_TESTRIG: {
            boolean generateDataplane = true;

            if (options.size() == 1) {
               if (options.get(0).equals("-nodataplane")) {
                  generateDataplane = false;
               }
               else {
                  _logger.outputf("Unknown option %s\n", options.get(0));
                  return false;
               }
            }

            String testrigLocation = parameters.get(0);
            String testrigName = (parameters.size() > 1) ? parameters.get(1)
                  : DEFAULT_TESTRIG_PREFIX + UUID.randomUUID().toString();

            // initialize the container if it hasn't been init'd before
            if (!isSetContainer(false)) {
               _currContainerName = _workHelper
                     .initContainer(DEFAULT_CONTAINER_PREFIX);
               _logger.outputf("Init'ed and set active container to %s\n",
                     _currContainerName);
            }

            if (!uploadTestrigOrEnv(testrigLocation, testrigName, true)) {
               return false;
            }

            _logger.output("Uploaded testrig. Parsing now.\n");

            WorkItem wItemParse = _workHelper.getWorkItemParse(
                  _currContainerName, testrigName);

            if (!execute(wItemParse, outWriter)) {
               return false;
            }

            if (command.equals(COMMAND_INIT_TESTRIG)) {
               _currTestrig = testrigName;
               _currEnv = DEFAULT_ENV_NAME;
               _logger.outputf("Base testrig is now %s\n", _currTestrig);
            }
            else {
               _currDiffTestrig = testrigName;
               _currDiffEnv = DEFAULT_ENV_NAME;
               _logger.outputf("Diff testrig is now %s\n", _currTestrig);
            }

            if (generateDataplane) {
               _logger.output("Generating dataplane now\n");

               if (!generateDataplane(outWriter)) {
                  return false;
               }

               _logger.output("Generated dataplane\n");
            }

            return true;
         }
         case COMMAND_LIST_CONTAINERS: {
            String[] containerList = _workHelper.listContainers();
            _logger.outputf("Containers: %s\n", Arrays.toString(containerList));
            return true;
         }
         case COMMAND_LIST_ENVIRONMENTS: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }

            String[] environmentList = _workHelper.listEnvironments(
                  _currContainerName, _currTestrig);
            _logger.outputf("Environments: %s\n",
                  Arrays.toString(environmentList));

            return true;
         }
         case COMMAND_LIST_QUESTIONS: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }
            String[] questionList = _workHelper.listQuestions(
                  _currContainerName, _currTestrig);
            _logger.outputf("Questions: %s\n", Arrays.toString(questionList));
            return true;
         }
         case COMMAND_LIST_TESTRIGS: {
            Map<String, String> testrigs = _workHelper
                  .listTestrigs(_currContainerName);
            if (testrigs != null) {
               for (String testrigName : testrigs.keySet()) {
                  _logger.outputf("Testrig: %s\n%s\n", testrigName,
                        testrigs.get(testrigName));
               }
            }
            return true;
         }
         case COMMAND_PROMPT: {
            if (_settings.getRunMode() == RunMode.interactive) {
               _logger.output("\n\n[Press enter to proceed]\n\n");
               BufferedReader in = new BufferedReader(new InputStreamReader(
                     System.in));
               in.readLine();
            }
            return true;
         }
         case COMMAND_PWD: {
            final String dir = System.getProperty("user.dir");
            _logger.output("working directory = " + dir + "\n");
            return true;
         }
         case COMMAND_SET_BATFISH_LOGLEVEL: {
            String logLevelStr = parameters.get(0).toLowerCase();
            if (!BatfishLogger.isValidLogLevel(logLevelStr)) {
               _logger.errorf("Undefined loglevel value: %s\n", logLevelStr);
               return false;
            }
            _settings.setBatfishLogLevel(logLevelStr);
            _logger.output("Changed batfish loglevel to " + logLevelStr + "\n");
            return true;
         }
         case COMMAND_SET_CONTAINER: {
            _currContainerName = parameters.get(0);
            _logger.outputf("Active container is now set to %s\n",
                  _currContainerName);
            return true;
         }
         case COMMAND_SET_DIFF_ENV: {
            _currDiffEnv = parameters.get(0);
            if (_currDiffTestrig == null) {
               _currDiffTestrig = _currTestrig;
            }
            _logger.outputf("Active diff testrig->environment is now %s->%s\n",
                  _currDiffTestrig, _currDiffEnv);
            return true;
         }
         case COMMAND_SET_ENV: {
            if (!isSetTestrig()) {
               return false;
            }
            _currEnv = parameters.get(0);
            _logger.outputf("Base testrig->env is now %s->%s\n", _currTestrig,
                  _currEnv);
            return true;
         }
         case COMMAND_SET_DIFF_TESTRIG: {
            _currDiffTestrig = parameters.get(0);
            _currDiffEnv = (parameters.size() > 1) ? parameters.get(1)
                  : DEFAULT_ENV_NAME;
            _logger.outputf("Diff testrig->env is now %s->%s\n",
                  _currDiffTestrig, _currDiffEnv);
            return true;
         }
         case COMMAND_SET_LOGLEVEL: {
            String logLevelStr = parameters.get(0).toLowerCase();
            if (!BatfishLogger.isValidLogLevel(logLevelStr)) {
               _logger.errorf("Undefined loglevel value: %s\n", logLevelStr);
               return false;
            }
            _logger.setLogLevel(logLevelStr);
            _logger.output("Changed client loglevel to " + logLevelStr + "\n");
            return true;
         }
         case COMMAND_SET_TESTRIG: {
            if (!isSetContainer(true)) {
               return false;
            }

            _currTestrig = parameters.get(0);
            _currEnv = (parameters.size() > 1) ? parameters.get(1)
                  : DEFAULT_ENV_NAME;
            _logger.outputf("Base testrig->env is now %s->%s\n", _currTestrig,
                  _currEnv);
            return true;
         }
         case COMMAND_SHOW_API_KEY: {
            _logger.outputf("Current API Key is %s\n", _settings.getApiKey());
            return true;
         }
         case COMMAND_SHOW_BATFISH_LOGLEVEL: {
            _logger.outputf("Current batfish log level is %s\n",
                  _settings.getBatfishLogLevel());
            return true;
         }
         case COMMAND_SHOW_CONTAINER: {
            _logger.outputf("Current container is %s\n", _currContainerName);
            return true;
         }
         case COMMAND_SHOW_COORDINATOR_HOST: {
            _logger.outputf("Current coordinator host is %s\n",
                  _settings.getCoordinatorHost());
            return true;
         }
         case COMMAND_SHOW_LOGLEVEL: {
            _logger.outputf("Current client log level is %s\n",
                  _logger.getLogLevelStr());
            return true;
         }
         case COMMAND_SHOW_DIFF_TESTRIG: {
            if (!isSetDiffEnvironment()) {
               return false;
            }
            _logger.outputf("Diff testrig->environment is %s->%s\n",
                  _currDiffTestrig, _currDiffEnv);
            return true;
         }
         case COMMAND_SHOW_TESTRIG: {
            if (!isSetTestrig()) {
               return false;
            }
            _logger.outputf("Base testrig->environment is %s->%s\n",
                  _currTestrig, _currEnv);
            return true;
         }
         case COMMAND_TEST: {
            String referenceFileName = parameters.get(0);
            
            String[] testCommand = parameters.subList(1, parameters.size())
                  .toArray(new String[0]);

            _logger.debugf("Ref file is %s. \n", referenceFileName, parameters.size());            
            _logger.debugf("Test command is %s\n", Arrays.toString(testCommand));

            File referenceFile = new File(referenceFileName);
            
            if (!referenceFile.exists()) {
               _logger.errorf("Reference file does not exist: %s\n", referenceFileName);
               return false;
            }
            
            File testoutFile = Files.createTempFile("test", "out").toFile();
            testoutFile.deleteOnExit();

            FileWriter testoutWriter = new FileWriter(testoutFile);
            
            processCommand(testCommand, testoutWriter);
            testoutWriter.close();
            
            boolean testPassed = false;
            
            try {
               String referenceOutput = CommonUtil.readFile(Paths.get(referenceFileName));
               String testOutput = CommonUtil.readFile(Paths.get(testoutFile.getAbsolutePath()));

               ObjectMapper mapper = new BatfishObjectMapper();
               JsonNode referenceJson = mapper.readTree(referenceOutput);
               JsonNode testJson = mapper.readTree(testOutput);
               if (CommonUtil.checkJsonEqual(referenceJson, testJson)) {
                  testPassed = true;
               }
            }
            catch (Exception e) {
               _logger.errorf("Exception in comparing test results: ", e.getMessage());
            }
            
            if (testPassed) {
               _logger.outputf("Test result for %s: Pass\n", referenceFileName);
            }
            else {
               String outFileName = referenceFile + ".testout";
               Files.move(Paths.get(testoutFile.getAbsolutePath()),  
                     Paths.get(referenceFile + ".testout"), 
                     StandardCopyOption.REPLACE_EXISTING);

               _logger.outputf("Test result for %s: Fail\n", referenceFileName);
               _logger.outputf("Copied output to %s\n", outFileName);
            }

            return true;
         }
         case COMMAND_UPLOAD_CUSTOM_OBJECT: {
            if (!isSetTestrig() || !isSetContainer(true)) {
               return false;
            }

            String objectName = parameters.get(0);
            String objectFile = parameters.get(1);

            // upload the object
            return _workHelper.uploadCustomObject(_currContainerName,
                  _currTestrig, objectName, objectFile);
         }
         default:
            _logger.error("Unsupported command " + words[0] + "\n");
            _logger.error("Type 'help' to see the list of valid commands\n");
            return false;
         }
      }
      catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   private boolean processCommands(List<String> commands) {
      for (String command : commands) {
         if (!processCommand(command)) {
            return false;
         }
      }
      return true;
   }

   public void run(List<String> initialCommands) {
      initHelpers();

      _logger.debugf("Will use coordinator at %s://%s\n",
            (_settings.getUseSsl()) ? "https" : "http",
            _settings.getCoordinatorHost());

      if (!processCommands(initialCommands)) {
         return;
      }

      // set container if specified
      if (_settings.getContainerId() != null) {
         if (!processCommand(COMMAND_SET_CONTAINER + "  "
               + _settings.getContainerId())) {
            return;
         }
      }

      // set testrig if dir or id is specified
      if (_settings.getTestrigDir() != null) {
         if (_settings.getTestrigId() != null) {
            System.err
                  .println("org.batfish.client: Cannot supply both testrigDir and testrigId.");
            System.exit(1);
         }
         if (!processCommand(COMMAND_INIT_TESTRIG + " -nodataplane "
               + _settings.getTestrigDir())) {
            return;
         }
      }
      if (_settings.getTestrigId() != null) {
         if (!processCommand(COMMAND_SET_TESTRIG + "  "
               + _settings.getTestrigId())) {
            return;
         }
      }

      switch (_settings.getRunMode()) {
      case batch:
         List<String> commands = null;
         try {
            commands = Files.readAllLines(
                  Paths.get(_settings.getBatchCommandFile()),
                  StandardCharsets.US_ASCII);
         }
         catch (Exception e) {
            System.err.printf("Exception in reading command file %s: %s",
                  _settings.getBatchCommandFile(), e.getMessage());
            System.exit(1);
         }
         processCommands(commands);

         break;
      case genquestions:
         generateQuestions();
         break;
      case interactive:
         runInteractive();
         break;
      default:
         System.err.println("org.batfish.client: Unknown run mode.");
         System.exit(1);
      }
   }

   private void runInteractive() {
      try {

         String rawLine;
         while ((rawLine = _reader.readLine()) != null) {
            String line = rawLine.trim();
            if (line.length() == 0) {
               continue;
            }

            if (line.equals(COMMAND_CLEAR_SCREEN)) {
               _reader.clearScreen();
               continue;
            }

            String[] words = line.split("\\s+");

            if (words.length > 0) {
               if (validCommandUsage(words)) {
                  processCommand(words, null);
               }
            }
         }
      }
      catch (Throwable t) {
         t.printStackTrace();
      }
   }

   private boolean uploadTestrigOrEnv(String fileOrDir,
         String testrigOrEnvName, boolean isTestrig) throws Exception {

      File filePointer = new File(fileOrDir);

      String uploadFilename = fileOrDir;

      if (filePointer.isDirectory()) {
         File uploadFile = File.createTempFile("testrigOrEnv", "zip");
         uploadFile.deleteOnExit();
         uploadFilename = uploadFile.getAbsolutePath();
         ZipUtility.zipFiles(filePointer.getAbsolutePath(), uploadFilename);
      }

      boolean result = (isTestrig) ? _workHelper.uploadTestrig(
            _currContainerName, testrigOrEnvName, uploadFilename) : _workHelper
            .uploadEnvironment(_currContainerName, _currTestrig,
                  testrigOrEnvName, uploadFilename);

      // unequal means we must have created a temporary file
      if (uploadFilename != fileOrDir) {
         new File(uploadFilename).delete();
      }

      return result;
   }

   private boolean validCommandUsage(String[] words) {
      return true;
   }
}
