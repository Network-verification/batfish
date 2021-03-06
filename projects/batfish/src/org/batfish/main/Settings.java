package org.batfish.main;

import java.nio.file.Path;
import java.util.List;

import org.batfish.common.BaseSettings;
import org.batfish.common.BatfishLogger;
import org.batfish.common.BfConsts;
import org.batfish.common.CoordConsts;
import org.batfish.common.util.CommonUtil;
import org.batfish.config.ConfigurationLocator;

public final class Settings extends BaseSettings {

   public static final class EnvironmentSettings {

      private Path _controlPlaneFactsDir;

      private Path _dataPlanePath;

      private Path _deltaCompiledConfigurationsDir;

      private Path _deltaConfigurationsDir;

      private Path _deltaVendorConfigurationsDir;

      private Path _edgeBlacklistPath;

      private Path _envPath;

      private Path _externalBgpAnnouncementsPath;

      private Path _interfaceBlacklistPath;

      private String _name;

      private Path _nlsDataPlaneInputFile;

      private Path _nlsDataPlaneOutputDir;

      private Path _nlsTrafficInputFile;

      private Path _nlsTrafficOutputDir;

      private Path _nodeBlacklistPath;

      private Path _precomputedRoutesPath;

      private Path _serializedTopologyPath;

      private Path _trafficFactsDir;

      private Path _z3File;

      public Path getControlPlaneFactsDir() {
         return _controlPlaneFactsDir;
      }

      public Path getDataPlanePath() {
         return _dataPlanePath;
      }

      public Path getDeltaCompiledConfigurationsDir() {
         return _deltaCompiledConfigurationsDir;
      }

      public Path getDeltaConfigurationsDir() {
         return _deltaConfigurationsDir;
      }

      public Path getDeltaVendorConfigurationsDir() {
         return _deltaVendorConfigurationsDir;
      }

      public Path getEdgeBlacklistPath() {
         return _edgeBlacklistPath;
      }

      public Path getEnvPath() {
         return _envPath;
      }

      public Path getExternalBgpAnnouncementsPath() {
         return _externalBgpAnnouncementsPath;
      }

      public Path getInterfaceBlacklistPath() {
         return _interfaceBlacklistPath;
      }

      public String getName() {
         return _name;
      }

      public Path getNlsDataPlaneInputFile() {
         return _nlsDataPlaneInputFile;
      }

      public Path getNlsDataPlaneOutputDir() {
         return _nlsDataPlaneOutputDir;
      }

      public Path getNlsTrafficInputFile() {
         return _nlsTrafficInputFile;
      }

      public Path getNlsTrafficOutputDir() {
         return _nlsTrafficOutputDir;
      }

      public Path getNodeBlacklistPath() {
         return _nodeBlacklistPath;
      }

      public Path getPrecomputedRoutesPath() {
         return _precomputedRoutesPath;
      }

      public Path getSerializedTopologyPath() {
         return _serializedTopologyPath;
      }

      public Path getTrafficFactsDir() {
         return _trafficFactsDir;
      }

      public Path getZ3File() {
         return _z3File;
      }

      public void setControlPlaneFactsDir(Path path) {
         _controlPlaneFactsDir = path;
      }

      public void setDataPlanePath(Path path) {
         _dataPlanePath = path;
      }

      public void setDeltaCompiledConfigurationsDir(
            Path deltaCompiledConfigurationsDir) {
         _deltaCompiledConfigurationsDir = deltaCompiledConfigurationsDir;
      }

      public void setDeltaConfigurationsDir(Path deltaConfigurationsDir) {
         _deltaConfigurationsDir = deltaConfigurationsDir;
      }

      public void setDeltaVendorConfigurationsDir(
            Path deltaVendorConfigurationsDir) {
         _deltaVendorConfigurationsDir = deltaVendorConfigurationsDir;
      }

      public void setEdgeBlacklistPath(Path edgeBlacklistPath) {
         _edgeBlacklistPath = edgeBlacklistPath;
      }

      public void setEnvPath(Path envPath) {
         _envPath = envPath;
      }

      public void setExternalBgpAnnouncementsPath(
            Path externalBgpAnnouncementsPath) {
         _externalBgpAnnouncementsPath = externalBgpAnnouncementsPath;
      }

      public void setInterfaceBlacklistPath(Path interfaceBlacklistPath) {
         _interfaceBlacklistPath = interfaceBlacklistPath;
      }

      public void setName(String name) {
         _name = name;
      }

      public void setNlsDataPlaneInputFile(Path nlsDataPlaneInputFile) {
         _nlsDataPlaneInputFile = nlsDataPlaneInputFile;
      }

      public void setNlsDataPlaneOutputDir(Path nlsDataPlaneOutputDir) {
         _nlsDataPlaneOutputDir = nlsDataPlaneOutputDir;
      }

      public void setNlsTrafficInputFile(Path nlsTrafficInputFile) {
         _nlsTrafficInputFile = nlsTrafficInputFile;
      }

      public void setNlsTrafficOutputDir(Path nlsTrafficOutputDir) {
         _nlsTrafficOutputDir = nlsTrafficOutputDir;
      }

      public void setNodeBlacklistPath(Path nodeBlacklistPath) {
         _nodeBlacklistPath = nodeBlacklistPath;
      }

      public void setPrecomputedRoutesPath(Path writeRoutesPath) {
         _precomputedRoutesPath = writeRoutesPath;
      }

      public void setSerializedTopologyPath(Path serializedTopologyPath) {
         _serializedTopologyPath = serializedTopologyPath;
      }

      public void setTrafficFactsDir(Path trafficFactsDir) {
         _trafficFactsDir = trafficFactsDir;
      }

      public void setZ3DataPlaneFile(Path path) {
         _z3File = path;
      }

   }

   public static final class TestrigSettings {

      private Path _basePath;

      private Path _convertAnswerPath;

      private EnvironmentSettings _environmentSettings;

      private String _name;

      private Path _parseAnswerPath;

      private Path _protocolDependencyGraphPath;

      private Path _protocolDependencyGraphZipPath;

      private Path _serializeIndependentPath;

      private Path _serializeVendorPath;

      private Path _testRigPath;

      public TestrigSettings() {
         _environmentSettings = new EnvironmentSettings();
      }

      public Path getBasePath() {
         return _basePath;
      }

      public Path getConvertAnswerPath() {
         return _convertAnswerPath;
      }

      public EnvironmentSettings getEnvironmentSettings() {
         return _environmentSettings;
      }

      public String getName() {
         return _name;
      }

      public Path getParseAnswerPath() {
         return _parseAnswerPath;
      }

      public Path getProtocolDependencyGraphPath() {
         return _protocolDependencyGraphPath;
      }

      public Path getProtocolDependencyGraphZipPath() {
         return _protocolDependencyGraphZipPath;
      }

      public Path getSerializeIndependentPath() {
         return _serializeIndependentPath;
      }

      public Path getSerializeVendorPath() {
         return _serializeVendorPath;
      }

      public Path getTestRigPath() {
         return _testRigPath;
      }

      public void setBasePath(Path basePath) {
         _basePath = basePath;
      }

      public void setConvertAnswerPath(Path convertAnswerPath) {
         _convertAnswerPath = convertAnswerPath;
      }

      public void setEnvironmentSettings(EnvironmentSettings environmentSettings) {
         _environmentSettings = environmentSettings;
      }

      public void setName(String name) {
         _name = name;
      }

      public void setParseAnswerPath(Path parseAnswerPath) {
         _parseAnswerPath = parseAnswerPath;
      }

      public void setProtocolDependencyGraphPath(
            Path protocolDependencyGraphPath) {
         _protocolDependencyGraphPath = protocolDependencyGraphPath;
      }

      public void setProtocolDependencyGraphZipPath(
            Path protocolDependencyGraphZipPath) {
         _protocolDependencyGraphZipPath = protocolDependencyGraphZipPath;
      }

      public void setSerializeIndependentPath(Path path) {
         _serializeIndependentPath = path;
      }

      public void setSerializeVendorPath(Path path) {
         _serializeVendorPath = path;
      }

      public void setTestRigPath(Path path) {
         _testRigPath = path;
      }

   }

   private static final String ARG_ANONYMIZE = "anonymize";

   private static final String ARG_BUILD_PREDICATE_INFO = "bpi";

   public static final String ARG_COORDINATOR_HOST = "coordinatorhost";

   private static final String ARG_COORDINATOR_NO_SSL = "coordinator.NoSsl";

   private static final String ARG_COORDINATOR_POOL_PORT = "coordinatorpoolport";

   public static final String ARG_COORDINATOR_REGISTER = "register";

   private static final String ARG_COORDINATOR_WORK_PORT = "coordinatorworkport";

   private static final String ARG_DIFF_QUESTION = "diffquestion";

   private static final String ARG_DISABLE_Z3_SIMPLIFICATION = "nosimplify";

   private static final String ARG_EXIT_ON_FIRST_ERROR = "ee";

   private static final String ARG_FLATTEN = "flatten";

   private static final String ARG_FLATTEN_DESTINATION = "flattendst";

   private static final String ARG_FLATTEN_ON_THE_FLY = "flattenonthefly";

   private static final String ARG_GEN_OSPF_TOPLOGY_PATH = "genospf";

   private static final String ARG_GENERATE_STUBS = "gs";

   private static final String ARG_GENERATE_STUBS_INPUT_ROLE = "gsinputrole";

   private static final String ARG_GENERATE_STUBS_INTERFACE_DESCRIPTION_REGEX = "gsidregex";

   private static final String ARG_GENERATE_STUBS_REMOTE_AS = "gsremoteas";

   private static final String ARG_HELP = "help";

   private static final String ARG_HISTOGRAM = "histogram";

   private static final String ARG_IGNORE_UNSUPPORTED = "ignoreunsupported";

   private static final String ARG_JOBS = "jobs";

   private static final String ARG_LOG_TEE = "logtee";

   private static final String ARG_MAX_PARSER_CONTEXT_LINES = "maxparsercontextlines";

   private static final String ARG_MAX_PARSER_CONTEXT_TOKENS = "maxparsercontexttokens";

   private static final String ARG_MAX_RUNTIME_MS = "maxruntime";

   private static final String ARG_NLS_DEBUG_SYMBOLS = "nlsdebugsymbols";

   private static final String ARG_NLS_DRY = "nlsdry";

   private static final String ARG_NO_OUTPUT = "nooutput";

   private static final String ARG_NO_SHUFFLE = "noshuffle";

   private static final String ARG_PRECOMPUTED_ADVERTISEMENTS_PATH = "precomputedadvertisementspath";

   private static final String ARG_PRECOMPUTED_FACTS_PATH = "precomputedfactspath";

   private static final String ARG_PRECOMPUTED_IBGP_NEIGHBORS_PATH = "precomputedibgpneighborspath";

   private static final String ARG_PRECOMPUTED_ROUTES_PATH = "precomputedroutespath";

   private static final String ARG_PRECOMPUTED_ROUTES_PATHS = "precomputedroutespaths";

   private static final String ARG_PREDHELP = "predhelp";

   private static final String ARG_PREDICATES = "predicates";

   private static final String ARG_PRINT_PARSE_TREES = "ppt";

   private static final String ARG_PRINT_SYMMETRIC_EDGES = "printsymmetricedges";

   private static final String ARG_QUERY = "query";

   private static final String ARG_QUERY_ALL = "all";

   private static final String ARG_SEQUENTIAL = "sequential";

   private static final String ARG_SERIALIZE_TO_TEXT = "stext";

   private static final String ARG_SERVICE_HOST = "servicehost";

   public static final String ARG_SERVICE_MODE = "servicemode";

   private static final String ARG_SERVICE_PORT = "serviceport";

   private static final String ARG_THROW_ON_LEXER_ERROR = "throwlexer";

   private static final String ARG_THROW_ON_PARSER_ERROR = "throwparser";

   private static final String ARG_TIMESTAMP = "timestamp";

   /**
    * (not wired to command line)
    */
   private static final String ARG_TRUST_ALL_SSL_CERTS = "batfish.TrustAllSslCerts";

   private static final String ARG_USE_PRECOMPUTED_FACTS = "useprecomputedfacts";

   private static final String ARGNAME_AS = "as";

   private static final String ARGNAME_HOSTNAME = "hostname";

   private static final String ARGNAME_JAVA_REGEX = "java-regex";

   private static final String ARGNAME_LOG_LEVEL = "level-name";

   private static final String ARGNAME_NAME = "name";

   private static final String ARGNAME_NUMBER = "number";

   private static final String ARGNAME_PATH = "path";

   private static final String ARGNAME_PATHS = "path..";

   private static final String ARGNAME_PORT = "port";

   private static final String ARGNAME_PREDICATES = "predicates";

   private static final String ARGNAME_ROLE = "role";

   private static final String EXECUTABLE_NAME = "batfish";

   private TestrigSettings _activeTestrigSettings;

   private boolean _anonymize;

   private boolean _answer;

   private Path _answerJsonPath;

   private List<String> _blockNames;

   private boolean _buildPredicateInfo;

   private boolean _canExecute;

   private boolean _compileDiffEnvironment;

   private Path _containerDir;

   private String _coordinatorHost;

   private int _coordinatorPoolPort;

   private boolean _coordinatorRegister;

   private boolean _coordinatorUseSsl;

   private int _coordinatorWorkPort;

   private boolean _dataPlane;

   private String _deltaEnvironmentName;

   private String _deltaTestrig;

   private TestrigSettings _deltaTestrigSettings;

   private boolean _diffActive;

   private boolean _diffQuestion;

   private String _environmentName;

   private boolean _exitOnFirstError;

   private boolean _flatten;

   private Path _flattenDestination;

   private boolean _flattenOnTheFly;

   private boolean _generateStubs;

   private String _generateStubsInputRole;

   private String _generateStubsInterfaceDescriptionRegex;

   private Integer _generateStubsRemoteAs;

   private Path _genOspfTopologyPath;

   private List<String> _helpPredicates;

   private boolean _histogram;

   private boolean _history;

   private boolean _ignoreUnsupported;

   private int _jobs;

   private boolean _keepBlocks;

   private String _logFile;

   private BatfishLogger _logger;

   private String _logicSrcDir;

   private String _logLevel;

   private boolean _logTee;

   private int _maxParserContextLines;

   private int _maxParserContextTokens;

   private int _maxRuntimeMs;

   private boolean _nlsDataPlane;

   private boolean _nlsDebugSymbols;

   private boolean _nlsDry;

   private boolean _nlsTraffic;

   private Path _nodeRolesPath;

   private boolean _noOutput;

   private String _outputEnvironmentName;

   private boolean _pedanticAsError;

   private boolean _pedanticRecord;

   private Path _precomputedBgpAdvertisementsPath;

   private Path _precomputedFactsPath;

   private Path _precomputedIbgpNeighborsPath;

   private Path _precomputedRoutesPath;

   private List<Path> _precomputedRoutesPaths;

   private List<String> _predicates;

   private boolean _printParseTree;

   private boolean _printSemantics;

   private boolean _printSymmetricEdges;

   private boolean _query;

   private boolean _queryAll;

   private String _questionName;

   private Path _questionParametersPath;

   private Path _questionPath;

   private boolean _redFlagAsError;

   private boolean _redFlagRecord;

   private boolean _removeBlocks;

   private boolean _runInServiceMode;

   private boolean _sequential;

   private boolean _serializeIndependent;

   private boolean _serializeToText;

   private boolean _serializeVendor;

   private String _serviceHost;

   private int _servicePort;

   private boolean _shuffleJobs;

   private boolean _simplify;

   private boolean _synthesizeJsonTopology;

   private boolean _synthesizeTopology;

   private String _testrig;

   private TestrigSettings _testrigSettings;

   private boolean _throwOnLexerError;

   private boolean _throwOnParserError;

   private boolean _timestamp;

   private boolean _trustAllSslCerts;

   private boolean _unimplementedAsError;

   private boolean _unimplementedRecord;

   private boolean _unrecognizedAsRedFlag;

   private boolean _usePrecomputedAdvertisements;

   private boolean _usePrecomputedFacts;

   private boolean _usePrecomputedIbgpNeighbors;

   private boolean _usePrecomputedRoutes;

   private boolean _writeBgpAdvertisements;

   private boolean _writeControlPlaneFacts;

   private boolean _writeIbgpNeighbors;

   private boolean _writeRoutes;

   public Settings() {
      this(new String[] {});
   }

   public Settings(String[] args) {
      super(CommonUtil.getConfigProperties(ConfigurationLocator.class,
            BfConsts.RELPATH_CONFIG_FILE_NAME_BATFISH));
      _testrigSettings = new TestrigSettings();
      _deltaTestrigSettings = new TestrigSettings();
      initConfigDefaults();
      initOptions();
      parseCommandLine(args);
   }

   public boolean canExecute() {
      return _canExecute;
   }

   public boolean flattenOnTheFly() {
      return _flattenOnTheFly;
   }

   public TestrigSettings getActiveTestrigSettings() {
      return _activeTestrigSettings;
   }

   public boolean getAnonymize() {
      return _anonymize;
   }

   public boolean getAnswer() {
      return _answer;
   }

   public Path getAnswerJsonPath() {
      return _answerJsonPath;
   }

   public List<String> getBlockNames() {
      return _blockNames;
   }

   public boolean getBuildPredicateInfo() {
      return _buildPredicateInfo;
   }

   public boolean getCompileEnvironment() {
      return _compileDiffEnvironment;
   }

   public Path getContainerDir() {
      return _containerDir;
   }

   public String getCoordinatorHost() {
      return _coordinatorHost;
   }

   public int getCoordinatorPoolPort() {
      return _coordinatorPoolPort;
   }

   public boolean getCoordinatorRegister() {
      return _coordinatorRegister;
   }

   public boolean getCoordinatorUseSsl() {
      return _coordinatorUseSsl;
   }

   public int getCoordinatorWorkPort() {
      return _coordinatorWorkPort;
   }

   public boolean getDataPlane() {
      return _dataPlane;
   }

   public String getDeltaEnvironmentName() {
      return _deltaEnvironmentName;
   }

   public String getDeltaTestrig() {
      return _deltaTestrig;
   }

   public TestrigSettings getDeltaTestrigSettings() {
      return _deltaTestrigSettings;
   }

   public boolean getDiffActive() {
      return _diffActive;
   }

   public boolean getDiffQuestion() {
      return _diffQuestion;
   }

   public boolean getDumpControlPlaneFacts() {
      return _writeControlPlaneFacts;
   }

   public String getEnvironmentName() {
      return _environmentName;
   }

   public boolean getExitOnFirstError() {
      return _exitOnFirstError;
   }

   public boolean getFlatten() {
      return _flatten;
   }

   public Path getFlattenDestination() {
      return _flattenDestination;
   }

   public Path getGenerateOspfTopologyPath() {
      return _genOspfTopologyPath;
   }

   public boolean getGenerateStubs() {
      return _generateStubs;
   }

   public String getGenerateStubsInputRole() {
      return _generateStubsInputRole;
   }

   public String getGenerateStubsInterfaceDescriptionRegex() {
      return _generateStubsInterfaceDescriptionRegex;
   }

   public int getGenerateStubsRemoteAs() {
      return _generateStubsRemoteAs;
   }

   public List<String> getHelpPredicates() {
      return _helpPredicates;
   }

   public boolean getHistogram() {
      return _histogram;
   }

   public boolean getHistory() {
      return _history;
   }

   public int getJobs() {
      return _jobs;
   }

   public boolean getKeepBlocks() {
      return _keepBlocks;
   }

   public String getLogFile() {
      return _logFile;
   }

   public BatfishLogger getLogger() {
      return _logger;
   }

   public String getLogicSrcDir() {
      return _logicSrcDir;
   }

   public String getLogLevel() {
      return _logLevel;
   }

   public boolean getLogTee() {
      return _logTee;
   }

   public int getMaxParserContextLines() {
      return _maxParserContextLines;
   }

   public int getMaxParserContextTokens() {
      return _maxParserContextTokens;
   }

   public int getMaxRuntimeMs() {
      return _maxRuntimeMs;
   }

   public boolean getNlsDataPlane() {
      return _nlsDataPlane;
   }

   public boolean getNlsDebugSymbols() {
      return _nlsDebugSymbols;
   }

   public boolean getNlsDry() {
      return _nlsDry;
   }

   public boolean getNlsTraffic() {
      return _nlsTraffic;
   }

   public Path getNodeRolesPath() {
      return _nodeRolesPath;
   }

   public boolean getNoOutput() {
      return _noOutput;
   }

   public String getOutputEnvironmentName() {
      return _outputEnvironmentName;
   }

   public boolean getPedanticAsError() {
      return _pedanticAsError;
   }

   public boolean getPedanticRecord() {
      return _pedanticRecord;
   }

   public Path getPrecomputedBgpAdvertisementsPath() {
      return _precomputedBgpAdvertisementsPath;
   }

   public Path getPrecomputedFactsPath() {
      return _precomputedFactsPath;
   }

   public Path getPrecomputedIbgpNeighborsPath() {
      return _precomputedIbgpNeighborsPath;
   }

   public Path getPrecomputedRoutesPath() {
      return _precomputedRoutesPath;
   }

   public List<Path> getPrecomputedRoutesPaths() {
      return _precomputedRoutesPaths;
   }

   public List<String> getPredicates() {
      return _predicates;
   }

   public boolean getPrintSemantics() {
      return _printSemantics;
   }

   public boolean getPrintSymmetricEdgePairs() {
      return _printSymmetricEdges;
   }

   public boolean getQuery() {
      return _query;
   }

   public boolean getQueryAll() {
      return _queryAll;
   }

   public String getQuestionName() {
      return _questionName;
   }

   public Path getQuestionParametersPath() {
      return _questionParametersPath;
   }

   public Path getQuestionPath() {
      return _questionPath;
   }

   public boolean getRedFlagAsError() {
      return _redFlagAsError;
   }

   public boolean getRedFlagRecord() {
      return _redFlagRecord;
   }

   public boolean getRemoveBlocks() {
      return _removeBlocks;
   }

   public boolean getSequential() {
      return _sequential;
   }

   public boolean getSerializeIndependent() {
      return _serializeIndependent;
   }

   public boolean getSerializeToText() {
      return _serializeToText;
   }

   public boolean getSerializeVendor() {
      return _serializeVendor;
   }

   public String getServiceHost() {
      return _serviceHost;
   }

   public int getServicePort() {
      return _servicePort;
   }

   public boolean getShuffleJobs() {
      return _shuffleJobs;
   }

   public boolean getSimplify() {
      return _simplify;
   }

   public boolean getSynthesizeJsonTopology() {
      return _synthesizeJsonTopology;
   }

   public boolean getSynthesizeTopology() {
      return _synthesizeTopology;
   }

   public String getTestrig() {
      return _testrig;
   }

   public TestrigSettings getTestrigSettings() {
      return _testrigSettings;
   }

   public boolean getThrowOnLexerError() {
      return _throwOnLexerError;
   }

   public boolean getThrowOnParserError() {
      return _throwOnParserError;
   }

   public boolean getTimestamp() {
      return _timestamp;
   }

   public boolean getTrustAllSslCerts() {
      return _trustAllSslCerts;
   }

   public boolean getUnimplementedAsError() {
      return _unimplementedAsError;
   }

   public boolean getUnimplementedRecord() {
      return _unimplementedRecord;
   }

   public boolean getUnrecognizedAsRedFlag() {
      return _unrecognizedAsRedFlag;
   }

   public boolean getUsePrecomputedBgpAdvertisements() {
      return _usePrecomputedAdvertisements;
   }

   public boolean getUsePrecomputedFacts() {
      return _usePrecomputedFacts;
   }

   public boolean getUsePrecomputedIbgpNeighbors() {
      return _usePrecomputedIbgpNeighbors;
   }

   public boolean getUsePrecomputedRoutes() {
      return _usePrecomputedRoutes;
   }

   public boolean getWriteBgpAdvertisements() {
      return _writeBgpAdvertisements;
   }

   public boolean getWriteIbgpNeighbors() {
      return _writeIbgpNeighbors;
   }

   public boolean getWriteRoutes() {
      return _writeRoutes;
   }

   public boolean ignoreUnsupported() {
      return _ignoreUnsupported;
   }

   private void initConfigDefaults() {
      setDefaultProperty(ARG_ANONYMIZE, false);
      setDefaultProperty(BfConsts.ARG_ANSWER_JSON_PATH, null);
      setDefaultProperty(BfConsts.ARG_BLOCK_NAMES, new String[] {});
      setDefaultProperty(ARG_BUILD_PREDICATE_INFO, null);
      setDefaultProperty(ARG_COORDINATOR_REGISTER, false);
      setDefaultProperty(ARG_COORDINATOR_HOST, "localhost");
      setDefaultProperty(ARG_COORDINATOR_POOL_PORT, CoordConsts.SVC_POOL_PORT);
      setDefaultProperty(ARG_COORDINATOR_NO_SSL, CoordConsts.SVC_DISABLE_SSL);
      setDefaultProperty(ARG_COORDINATOR_WORK_PORT, CoordConsts.SVC_WORK_PORT);
      setDefaultProperty(BfConsts.ARG_DIFF_ACTIVE, false);
      setDefaultProperty(BfConsts.ARG_DELTA_ENVIRONMENT_NAME, null);
      setDefaultProperty(ARG_DIFF_QUESTION, false);
      setDefaultProperty(ARG_DISABLE_Z3_SIMPLIFICATION, false);
      setDefaultProperty(BfConsts.ARG_ENVIRONMENT_NAME, null);
      setDefaultProperty(ARG_EXIT_ON_FIRST_ERROR, false);
      setDefaultProperty(ARG_FLATTEN, false);
      setDefaultProperty(ARG_FLATTEN_DESTINATION, null);
      setDefaultProperty(ARG_FLATTEN_ON_THE_FLY, true);
      setDefaultProperty(ARG_GEN_OSPF_TOPLOGY_PATH, null);
      setDefaultProperty(ARG_GENERATE_STUBS, false);
      setDefaultProperty(ARG_GENERATE_STUBS_INPUT_ROLE, null);
      setDefaultProperty(ARG_GENERATE_STUBS_INTERFACE_DESCRIPTION_REGEX, null);
      setDefaultProperty(ARG_GENERATE_STUBS_REMOTE_AS, null);
      setDefaultProperty(ARG_HELP, false);
      setDefaultProperty(ARG_HISTOGRAM, false);
      setDefaultProperty(ARG_IGNORE_UNSUPPORTED, false);
      setDefaultProperty(ARG_JOBS, Integer.MAX_VALUE);
      setDefaultProperty(BfConsts.ARG_LOG_FILE, null);
      setDefaultProperty(ARG_LOG_TEE, false);
      setDefaultProperty(BfConsts.ARG_LOG_LEVEL, "debug");
      setDefaultProperty(ARG_MAX_PARSER_CONTEXT_LINES, 10);
      setDefaultProperty(ARG_MAX_PARSER_CONTEXT_TOKENS, 10);
      setDefaultProperty(ARG_MAX_RUNTIME_MS, 0);
      setDefaultProperty(ARG_NLS_DEBUG_SYMBOLS, false);
      setDefaultProperty(ARG_NLS_DRY, false);
      setDefaultProperty(ARG_NO_OUTPUT, false);
      setDefaultProperty(ARG_NO_SHUFFLE, false);
      setDefaultProperty(BfConsts.ARG_OUTPUT_ENV, null);
      setDefaultProperty(BfConsts.ARG_PEDANTIC_AS_ERROR, false);
      setDefaultProperty(BfConsts.ARG_PEDANTIC_SUPPRESS, false);
      setDefaultProperty(ARG_PRECOMPUTED_ADVERTISEMENTS_PATH, null);
      setDefaultProperty(ARG_PRECOMPUTED_FACTS_PATH, null);
      setDefaultProperty(ARG_PRECOMPUTED_IBGP_NEIGHBORS_PATH, null);
      setDefaultProperty(ARG_PRECOMPUTED_ROUTES_PATH, null);
      setDefaultProperty(ARG_PRECOMPUTED_ROUTES_PATHS, new String[] {});
      setDefaultProperty(ARG_PREDHELP, new String[] {});
      setDefaultProperty(ARG_PREDICATES, new String[] {});
      setDefaultProperty(ARG_PRINT_PARSE_TREES, false);
      setDefaultProperty(ARG_PRINT_SYMMETRIC_EDGES, false);
      setDefaultProperty(ARG_QUERY, false);
      setDefaultProperty(ARG_QUERY_ALL, false);
      setDefaultProperty(BfConsts.ARG_QUESTION_NAME, null);
      setDefaultProperty(BfConsts.ARG_RED_FLAG_AS_ERROR, false);
      setDefaultProperty(BfConsts.ARG_RED_FLAG_SUPPRESS, false);
      setDefaultProperty(ARG_SEQUENTIAL, false);
      setDefaultProperty(ARG_SERIALIZE_TO_TEXT, false);
      setDefaultProperty(ARG_SERVICE_HOST, "0.0.0.0");
      setDefaultProperty(ARG_SERVICE_MODE, false);
      setDefaultProperty(ARG_SERVICE_PORT, BfConsts.SVC_PORT);
      setDefaultProperty(BfConsts.ARG_SYNTHESIZE_JSON_TOPOLOGY, false);
      setDefaultProperty(BfConsts.ARG_SYNTHESIZE_TOPOLOGY, false);
      setDefaultProperty(ARG_THROW_ON_LEXER_ERROR, false);
      setDefaultProperty(ARG_THROW_ON_PARSER_ERROR, false);
      setDefaultProperty(ARG_TIMESTAMP, false);
      setDefaultProperty(ARG_TRUST_ALL_SSL_CERTS, true);
      setDefaultProperty(BfConsts.ARG_UNRECOGNIZED_AS_RED_FLAG, false);
      setDefaultProperty(BfConsts.ARG_USE_PRECOMPUTED_ADVERTISEMENTS, false);
      setDefaultProperty(ARG_USE_PRECOMPUTED_FACTS, false);
      setDefaultProperty(BfConsts.ARG_USE_PRECOMPUTED_IBGP_NEIGHBORS, false);
      setDefaultProperty(BfConsts.ARG_USE_PRECOMPUTED_ROUTES, false);
      setDefaultProperty(BfConsts.ARG_UNIMPLEMENTED_AS_ERROR, false);
      setDefaultProperty(BfConsts.ARG_UNIMPLEMENTED_SUPPRESS, true);
      setDefaultProperty(BfConsts.COMMAND_ANSWER, false);
      setDefaultProperty(BfConsts.COMMAND_COMPILE_DIFF_ENVIRONMENT, false);
      setDefaultProperty(BfConsts.COMMAND_DUMP_DP, false);
      setDefaultProperty(BfConsts.COMMAND_GET_HISTORY, false);
      setDefaultProperty(BfConsts.COMMAND_KEEP_BLOCKS, false);
      setDefaultProperty(BfConsts.COMMAND_NLS_DATA_PLANE, false);
      setDefaultProperty(BfConsts.COMMAND_NLS_TRAFFIC, false);
      setDefaultProperty(BfConsts.COMMAND_PARSE_VENDOR_INDEPENDENT, false);
      setDefaultProperty(BfConsts.COMMAND_PARSE_VENDOR_SPECIFIC, false);
      setDefaultProperty(BfConsts.COMMAND_REMOVE_BLOCKS, false);
      setDefaultProperty(BfConsts.COMMAND_WRITE_CP_FACTS, false);
      setDefaultProperty(BfConsts.COMMAND_WRITE_ADVERTISEMENTS, false);
      setDefaultProperty(BfConsts.COMMAND_WRITE_IBGP_NEIGHBORS, false);
      setDefaultProperty(BfConsts.COMMAND_WRITE_ROUTES, false);
   }

   private void initOptions() {

      addBooleanOption(ARG_ANONYMIZE,
            "created anonymized versions of configs in test rig");

      addOption(BfConsts.ARG_ANSWER_JSON_PATH,
            "save query json output to specified file", ARGNAME_PATH);

      addListOption(BfConsts.ARG_BLOCK_NAMES,
            "list of blocks of logic rules to add or remove", "blocknames");

      addOption(
            ARG_BUILD_PREDICATE_INFO,
            "build predicate info (should only be called by ant build script) with provided input logic dir",
            ARGNAME_PATH);

      addOption(BfConsts.ARG_CONTAINER_DIR, "path to container directory",
            ARGNAME_PATH);

      addOption(ARG_COORDINATOR_HOST,
            "hostname of coordinator for registration with -"
                  + ARG_SERVICE_MODE, ARGNAME_HOSTNAME);

      addOption(ARG_COORDINATOR_POOL_PORT,
            "coordinator pool manager listening port", ARGNAME_PORT);

      addBooleanOption(ARG_COORDINATOR_REGISTER,
            "register service with coordinator on startup");

      addBooleanOption(ARG_COORDINATOR_NO_SSL, "whether coordinator uses ssl");

      addOption(ARG_COORDINATOR_WORK_PORT,
            "coordinator work manager listening port", "port_number");

      addOption(BfConsts.ARG_DELTA_ENVIRONMENT_NAME,
            "name of delta environment to use", "name");

      addOption(BfConsts.ARG_DELTA_TESTRIG, "name of delta testrig",
            ARGNAME_NAME);

      addBooleanOption(
            BfConsts.ARG_DIFF_ACTIVE,
            "make differential environment the active one for questions about a single environment");

      addBooleanOption(
            ARG_DIFF_QUESTION,
            "force treatment of question as differential (to be used when not answering question)");

      addBooleanOption(ARG_DISABLE_Z3_SIMPLIFICATION,
            "disable z3 simplification");

      addOption(BfConsts.ARG_ENVIRONMENT_NAME, "name of environment to use",
            "name");

      addBooleanOption(ARG_EXIT_ON_FIRST_ERROR,
            "exit on first parse error (otherwise will exit on last parse error)");

      addBooleanOption(ARG_FLATTEN,
            "flatten hierarchical juniper configuration files");

      addOption(
            ARG_FLATTEN_DESTINATION,
            "output path to test rig in which flat juniper (and all other) configurations will be placed",
            ARGNAME_PATH);

      addBooleanOption(
            ARG_FLATTEN_ON_THE_FLY,
            "flatten hierarchical juniper configuration files on-the-fly (line number references will be spurious)");

      addOption(ARG_GEN_OSPF_TOPLOGY_PATH,
            "generate ospf configs from specified topology", ARGNAME_PATH);

      addBooleanOption(ARG_GENERATE_STUBS, "generate stubs");

      addOption(ARG_GENERATE_STUBS_INPUT_ROLE,
            "input role for which to generate stubs", ARGNAME_ROLE);

      addOption(
            ARG_GENERATE_STUBS_INTERFACE_DESCRIPTION_REGEX,
            "java regex to extract hostname of generated stub from description of adjacent interface",
            ARGNAME_JAVA_REGEX);

      addOption(ARG_GENERATE_STUBS_REMOTE_AS,
            "autonomous system number of stubs to be generated", ARGNAME_AS);

      addBooleanOption(ARG_HELP, "print this message");

      addBooleanOption(ARG_IGNORE_UNSUPPORTED,
            "ignore configuration files with unsupported format instead of crashing");

      addOption(ARG_JOBS, "number of threads used by parallel jobs executor",
            ARGNAME_NUMBER);

      addOption(BfConsts.ARG_LOG_LEVEL, "log level", ARGNAME_LOG_LEVEL);

      addBooleanOption(ARG_HISTOGRAM,
            "build histogram of unimplemented features");

      addOption(BfConsts.ARG_LOG_FILE, "path to main log file", ARGNAME_PATH);

      addBooleanOption(ARG_LOG_TEE,
            "print output to both logfile and standard out");

      addOption(ARG_MAX_PARSER_CONTEXT_LINES,
            "max number of surrounding lines to print on parser error",
            ARGNAME_NUMBER);

      addOption(ARG_MAX_PARSER_CONTEXT_TOKENS,
            "max number of context tokens to print on parser error",
            ARGNAME_NUMBER);

      addOption(ARG_MAX_RUNTIME_MS,
            "maximum time (in ms) to allow a task to run", ARGNAME_NUMBER);

      addBooleanOption(ARG_NLS_DEBUG_SYMBOLS,
            "compute/display nls debug symbols for querying");

      addBooleanOption(ARG_NLS_DRY,
            "prepare nls facts only without executing nls");

      addBooleanOption(ARG_NO_OUTPUT, "do not produce output files");

      addBooleanOption(ARG_NO_SHUFFLE, "do not shuffle parallel jobs");

      addOption(BfConsts.ARG_OUTPUT_ENV, "name of output environment",
            ARGNAME_NAME);

      addBooleanOption(
            BfConsts.ARG_PEDANTIC_AS_ERROR,
            "throws "
                  + PedanticBatfishException.class.getSimpleName()
                  + " for likely harmless warnings (e.g. deviation from good configuration style), instead of emitting warning and continuing");

      addBooleanOption(BfConsts.ARG_PEDANTIC_SUPPRESS,
            "suppresses pedantic warnings");

      addOption(ARG_PRECOMPUTED_ADVERTISEMENTS_PATH,
            "path to precomputed bgp advertisements", ARGNAME_PATH);

      addOption(ARG_PRECOMPUTED_FACTS_PATH, "path to precomputed facts",
            ARGNAME_PATH);

      addOption(ARG_PRECOMPUTED_IBGP_NEIGHBORS_PATH,
            "path to precomputed ibgp neighbors", ARGNAME_PATH);

      addOption(ARG_PRECOMPUTED_ROUTES_PATH,
            "output path to precomputed routes", ARGNAME_PATH);

      addListOption(ARG_PRECOMPUTED_ROUTES_PATHS,
            "input paths to precomputed routes", ARGNAME_PATHS);

      addListOption(
            ARG_PREDHELP,
            "print semantics for all predicates, or for predicates supplied as optional arguments",
            ARGNAME_PREDICATES);

      addListOption(ARG_PREDICATES, "list of predicates to query",
            ARGNAME_PREDICATES);

      addBooleanOption(ARG_PRINT_PARSE_TREES, "print parse trees");

      addBooleanOption(ARG_PRINT_SYMMETRIC_EDGES,
            "print topology with symmetric edges adjacent in listing");

      addBooleanOption(ARG_QUERY, "query one or more nls relations");

      addBooleanOption(ARG_QUERY_ALL, "query ALL predicates");

      addOption(BfConsts.ARG_QUESTION_NAME, "name of question", ARGNAME_NAME);

      addBooleanOption(
            BfConsts.ARG_RED_FLAG_AS_ERROR,
            "throws "
                  + RedFlagBatfishException.class.getSimpleName()
                  + " on some recoverable errors (e.g. bad config lines), instead of emitting warning and attempting to recover");

      addBooleanOption(BfConsts.ARG_RED_FLAG_SUPPRESS,
            "suppresses red-flag warnings");

      addBooleanOption(ARG_SEQUENTIAL, "force sequential operation");

      addBooleanOption(ARG_SERIALIZE_TO_TEXT, "serialize to text");

      addOption(ARG_SERVICE_HOST, "local hostname to report to coordinator",
            ARGNAME_HOSTNAME);

      addBooleanOption(ARG_SERVICE_MODE, "run in service mode");

      addOption(ARG_SERVICE_PORT, "port for batfish service", ARGNAME_PORT);

      addBooleanOption(BfConsts.ARG_SYNTHESIZE_JSON_TOPOLOGY,
            "synthesize json topology from interface ip subnet information");

      addBooleanOption(BfConsts.ARG_SYNTHESIZE_TOPOLOGY,
            "synthesize topology from interface ip subnet information");

      addOption(BfConsts.ARG_TESTRIG, "name of testrig", ARGNAME_NAME);

      addBooleanOption(ARG_THROW_ON_LEXER_ERROR,
            "throw exception immediately on lexer error");

      addBooleanOption(ARG_THROW_ON_PARSER_ERROR,
            "throw exception immediately on parser error");

      addBooleanOption(ARG_TIMESTAMP, "print timestamps in log messages");

      addBooleanOption(
            BfConsts.ARG_UNIMPLEMENTED_AS_ERROR,
            "throws "
                  + UnimplementedBatfishException.class.getSimpleName()
                  + " when encountering unimplemented configuration directives, instead of emitting warning and ignoring");

      addBooleanOption(BfConsts.ARG_UNIMPLEMENTED_SUPPRESS,
            "suppresses warnings about unimplemented configuration directives");

      addBooleanOption(
            BfConsts.ARG_UNRECOGNIZED_AS_RED_FLAG,
            "treat unrecognized configuration directives as red flags instead of force-crashing");

      addBooleanOption(BfConsts.ARG_USE_PRECOMPUTED_ADVERTISEMENTS,
            "add precomputed bgp advertisements to data plane model");

      addBooleanOption(ARG_USE_PRECOMPUTED_FACTS,
            "add precomputed facts to data plane model");

      addBooleanOption(BfConsts.ARG_USE_PRECOMPUTED_IBGP_NEIGHBORS,
            "add precomputed ibgp neighbors to data plane model");

      addBooleanOption(BfConsts.ARG_USE_PRECOMPUTED_ROUTES,
            "add precomputed routes to data plane model");

      addBooleanOption(BfConsts.COMMAND_ANSWER, "answer provided question");

      addBooleanOption(BfConsts.COMMAND_COMPILE_DIFF_ENVIRONMENT,
            "compile configurations for differential environment");

      addBooleanOption(BfConsts.COMMAND_DUMP_DP,
            "compute and serialize data plane");

      addBooleanOption(BfConsts.COMMAND_GET_HISTORY, "retrieve flow history");

      addBooleanOption(BfConsts.COMMAND_KEEP_BLOCKS,
            "activate only selected blocks of logic rules");

      addBooleanOption(BfConsts.COMMAND_NLS_DATA_PLANE,
            "compute data plane with nls");

      addBooleanOption(BfConsts.COMMAND_NLS_TRAFFIC,
            "compute traffic information from provided flows with nls");

      addBooleanOption(BfConsts.COMMAND_PARSE_VENDOR_INDEPENDENT,
            "serialize vendor-independent configs");

      addBooleanOption(BfConsts.COMMAND_PARSE_VENDOR_SPECIFIC,
            "serialize vendor configs");

      addBooleanOption(BfConsts.COMMAND_REMOVE_BLOCKS,
            "remove selected blocks of logic rules");

      addBooleanOption(BfConsts.COMMAND_WRITE_ADVERTISEMENTS,
            "write bgp advertisements from nls data plane model to disk");

      addBooleanOption(BfConsts.COMMAND_WRITE_CP_FACTS,
            "write control plane facts");

      addBooleanOption(BfConsts.COMMAND_WRITE_IBGP_NEIGHBORS,
            "write ibgp neighbors from nls data plane model to disk");

      addBooleanOption(BfConsts.COMMAND_WRITE_ROUTES,
            "write routes from nls data plane model to disk");

   }

   private void parseCommandLine(String[] args) {
      initCommandLine(args);
      _canExecute = true;
      _runInServiceMode = false;
      _printSemantics = false;

      // SPECIAL OPTIONS
      _logFile = getStringOptionValue(BfConsts.ARG_LOG_FILE);
      _logLevel = getStringOptionValue(BfConsts.ARG_LOG_LEVEL);
      if (getBooleanOptionValue(ARG_HELP)) {
         _canExecute = false;
         printHelp(EXECUTABLE_NAME);
         return;
      }
      _buildPredicateInfo = getStringOptionValue(ARG_BUILD_PREDICATE_INFO) != null;
      if (_buildPredicateInfo) {
         _logicSrcDir = getStringOptionValue(ARG_BUILD_PREDICATE_INFO);
         return;
      }
      _printSemantics = getStringListOptionValue(ARG_PREDHELP).size() != 0;

      // REGULAR OPTIONS
      _anonymize = getBooleanOptionValue(ARG_ANONYMIZE);
      _answer = getBooleanOptionValue(BfConsts.COMMAND_ANSWER);
      _answerJsonPath = getPathOptionValue(BfConsts.ARG_ANSWER_JSON_PATH);
      _blockNames = getStringListOptionValue(BfConsts.ARG_BLOCK_NAMES);
      _compileDiffEnvironment = getBooleanOptionValue(BfConsts.COMMAND_COMPILE_DIFF_ENVIRONMENT);
      _containerDir = getPathOptionValue(BfConsts.ARG_CONTAINER_DIR);
      _coordinatorHost = getStringOptionValue(ARG_COORDINATOR_HOST);
      _coordinatorPoolPort = getIntOptionValue(ARG_COORDINATOR_POOL_PORT);
      _coordinatorRegister = getBooleanOptionValue(ARG_COORDINATOR_REGISTER);
      _coordinatorUseSsl = !getBooleanOptionValue(ARG_COORDINATOR_NO_SSL);
      _coordinatorWorkPort = getIntOptionValue(ARG_COORDINATOR_WORK_PORT);
      _dataPlane = getBooleanOptionValue(BfConsts.COMMAND_DUMP_DP);
      _deltaEnvironmentName = getStringOptionValue(BfConsts.ARG_DELTA_ENVIRONMENT_NAME);
      _deltaTestrig = getStringOptionValue(BfConsts.ARG_DELTA_TESTRIG);
      _diffActive = getBooleanOptionValue(BfConsts.ARG_DIFF_ACTIVE);
      _diffQuestion = getBooleanOptionValue(ARG_DIFF_QUESTION);
      _environmentName = getStringOptionValue(BfConsts.ARG_ENVIRONMENT_NAME);
      _exitOnFirstError = getBooleanOptionValue(ARG_EXIT_ON_FIRST_ERROR);
      _flatten = getBooleanOptionValue(ARG_FLATTEN);
      _flattenDestination = getPathOptionValue(ARG_FLATTEN_DESTINATION);
      _flattenOnTheFly = getBooleanOptionValue(ARG_FLATTEN_ON_THE_FLY);
      _generateStubs = getBooleanOptionValue(ARG_GENERATE_STUBS);
      _generateStubsInputRole = getStringOptionValue(ARG_GENERATE_STUBS_INPUT_ROLE);
      _generateStubsInterfaceDescriptionRegex = getStringOptionValue(ARG_GENERATE_STUBS_INTERFACE_DESCRIPTION_REGEX);
      _generateStubsRemoteAs = getIntegerOptionValue(ARG_GENERATE_STUBS_REMOTE_AS);
      _genOspfTopologyPath = getPathOptionValue(ARG_GEN_OSPF_TOPLOGY_PATH);
      _helpPredicates = getStringListOptionValue(ARG_PREDHELP);
      _histogram = getBooleanOptionValue(ARG_HISTOGRAM);
      _history = getBooleanOptionValue(BfConsts.COMMAND_GET_HISTORY);
      _ignoreUnsupported = getBooleanOptionValue(ARG_IGNORE_UNSUPPORTED);
      _jobs = getIntOptionValue(ARG_JOBS);
      _keepBlocks = getBooleanOptionValue(BfConsts.COMMAND_KEEP_BLOCKS);
      _logTee = getBooleanOptionValue(ARG_LOG_TEE);
      _maxParserContextLines = getIntOptionValue(ARG_MAX_PARSER_CONTEXT_LINES);
      _maxParserContextTokens = getIntOptionValue(ARG_MAX_PARSER_CONTEXT_TOKENS);
      _maxRuntimeMs = getIntOptionValue(ARG_MAX_RUNTIME_MS);
      _noOutput = getBooleanOptionValue(ARG_NO_OUTPUT);
      _nlsDataPlane = getBooleanOptionValue(BfConsts.COMMAND_NLS_DATA_PLANE);
      _nlsDry = getBooleanOptionValue(ARG_NLS_DRY);
      _nlsTraffic = getBooleanOptionValue(BfConsts.COMMAND_NLS_TRAFFIC);
      _nlsDebugSymbols = getBooleanOptionValue(ARG_NLS_DEBUG_SYMBOLS);
      _outputEnvironmentName = getStringOptionValue(BfConsts.ARG_OUTPUT_ENV);
      _pedanticAsError = getBooleanOptionValue(BfConsts.ARG_PEDANTIC_AS_ERROR);
      _pedanticRecord = !getBooleanOptionValue(BfConsts.ARG_PEDANTIC_SUPPRESS);
      _precomputedBgpAdvertisementsPath = getPathOptionValue(ARG_PRECOMPUTED_ADVERTISEMENTS_PATH);
      _precomputedFactsPath = getPathOptionValue(ARG_PRECOMPUTED_FACTS_PATH);
      _precomputedIbgpNeighborsPath = getPathOptionValue(ARG_PRECOMPUTED_IBGP_NEIGHBORS_PATH);
      _precomputedRoutesPath = getPathOptionValue(ARG_PRECOMPUTED_ROUTES_PATH);
      _precomputedRoutesPaths = getPathListOptionValue(ARG_PRECOMPUTED_ROUTES_PATHS);
      _predicates = getStringListOptionValue(ARG_PREDICATES);
      _printParseTree = getBooleanOptionValue(ARG_PRINT_PARSE_TREES);
      _printSymmetricEdges = getBooleanOptionValue(ARG_PRINT_SYMMETRIC_EDGES);
      _query = getBooleanOptionValue(ARG_QUERY);
      _queryAll = getBooleanOptionValue(ARG_QUERY_ALL);
      _questionName = getStringOptionValue(BfConsts.ARG_QUESTION_NAME);
      _redFlagAsError = getBooleanOptionValue(BfConsts.ARG_RED_FLAG_AS_ERROR);
      _redFlagRecord = !getBooleanOptionValue(BfConsts.ARG_RED_FLAG_SUPPRESS);
      _removeBlocks = getBooleanOptionValue(BfConsts.COMMAND_REMOVE_BLOCKS);
      _runInServiceMode = getBooleanOptionValue(ARG_SERVICE_MODE);
      _sequential = getBooleanOptionValue(ARG_SEQUENTIAL);
      _serializeIndependent = getBooleanOptionValue(BfConsts.COMMAND_PARSE_VENDOR_INDEPENDENT);
      _serializeToText = getBooleanOptionValue(ARG_SERIALIZE_TO_TEXT);
      _serializeVendor = getBooleanOptionValue(BfConsts.COMMAND_PARSE_VENDOR_SPECIFIC);
      _serviceHost = getStringOptionValue(ARG_SERVICE_HOST);
      _servicePort = getIntOptionValue(ARG_SERVICE_PORT);
      _shuffleJobs = !getBooleanOptionValue(ARG_NO_SHUFFLE);
      _simplify = !getBooleanOptionValue(ARG_DISABLE_Z3_SIMPLIFICATION);
      _synthesizeJsonTopology = getBooleanOptionValue(BfConsts.ARG_SYNTHESIZE_JSON_TOPOLOGY);
      _synthesizeTopology = getBooleanOptionValue(BfConsts.ARG_SYNTHESIZE_TOPOLOGY);
      _testrig = getStringOptionValue(BfConsts.ARG_TESTRIG);
      _throwOnLexerError = getBooleanOptionValue(ARG_THROW_ON_LEXER_ERROR);
      _throwOnParserError = getBooleanOptionValue(ARG_THROW_ON_PARSER_ERROR);
      _timestamp = getBooleanOptionValue(ARG_TIMESTAMP);
      _trustAllSslCerts = getBooleanOptionValue(ARG_TRUST_ALL_SSL_CERTS);
      _unimplementedAsError = getBooleanOptionValue(BfConsts.ARG_UNIMPLEMENTED_AS_ERROR);
      _unimplementedRecord = !getBooleanOptionValue(BfConsts.ARG_UNIMPLEMENTED_SUPPRESS);
      _unrecognizedAsRedFlag = getBooleanOptionValue(BfConsts.ARG_UNRECOGNIZED_AS_RED_FLAG);
      _usePrecomputedAdvertisements = getBooleanOptionValue(BfConsts.ARG_USE_PRECOMPUTED_ADVERTISEMENTS);
      _usePrecomputedFacts = getBooleanOptionValue(ARG_USE_PRECOMPUTED_FACTS);
      _usePrecomputedIbgpNeighbors = getBooleanOptionValue(BfConsts.ARG_USE_PRECOMPUTED_IBGP_NEIGHBORS);
      _usePrecomputedRoutes = getBooleanOptionValue(BfConsts.ARG_USE_PRECOMPUTED_ROUTES);
      _writeBgpAdvertisements = getBooleanOptionValue(BfConsts.COMMAND_WRITE_ADVERTISEMENTS);
      _writeControlPlaneFacts = getBooleanOptionValue(BfConsts.COMMAND_WRITE_CP_FACTS);
      _writeIbgpNeighbors = getBooleanOptionValue(BfConsts.COMMAND_WRITE_IBGP_NEIGHBORS);
      _writeRoutes = getBooleanOptionValue(BfConsts.COMMAND_WRITE_ROUTES);
   }

   public boolean printParseTree() {
      return _printParseTree;
   }

   public boolean runInServiceMode() {
      return _runInServiceMode;
   }

   public void setActiveTestrigSettings(TestrigSettings activeTestrigSettings) {
      _activeTestrigSettings = activeTestrigSettings;
   }

   public void setDeltaEnvironmentName(String diffEnvironmentName) {
      _deltaEnvironmentName = diffEnvironmentName;
   }

   public void setDeltaTestrig(String deltaTestrig) {
      _deltaTestrig = deltaTestrig;
   }

   public void setDiffActive(boolean diffActive) {
      _diffActive = diffActive;
   }

   public void setDiffQuestion(boolean diffQuestion) {
      _diffQuestion = diffQuestion;
   }

   public void setDumpControlPlaneFacts(boolean dumpControlPlaneFacts) {
      _writeControlPlaneFacts = dumpControlPlaneFacts;
   }

   public void setEnvironmentName(String envName) {
      _environmentName = envName;
   }

   public void setHistory(boolean history) {
      _history = history;
   }

   public void setLogger(BatfishLogger logger) {
      _logger = logger;
   }

   public void setMaxParserContextLines(int maxParserContextLines) {
      _maxParserContextLines = maxParserContextLines;
   }

   public void setMaxParserContextTokens(int maxParserContextTokens) {
      _maxParserContextTokens = maxParserContextTokens;
   }

   public void setMaxRuntimeMs(int runtimeMs) {
      _maxRuntimeMs = runtimeMs;
   }

   public void setNlsDry(boolean nlsDry) {
      _nlsDry = nlsDry;
   }

   public void setNlsTraffic(boolean postFlows) {
      _nlsTraffic = postFlows;
   }

   public void setNodeRolesPath(Path nodeRolesPath) {
      _nodeRolesPath = nodeRolesPath;
   }

   public void setQuestionParametersPath(Path questionParametersPath) {
      _questionParametersPath = questionParametersPath;
   }

   public void setQuestionPath(Path questionPath) {
      _questionPath = questionPath;
   }

   public void setThrowOnLexerError(boolean throwOnLexerError) {
      _throwOnLexerError = throwOnLexerError;
   }

   public void setThrowOnParserError(boolean throwOnParserError) {
      _throwOnParserError = throwOnParserError;
   }

}
