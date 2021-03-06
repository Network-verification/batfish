package org.batfish.common;

public class BfConsts {

   public enum TaskStatus {
      InProgress,
      TerminatedAbnormally,
      TerminatedNormally,
      Unknown,
      UnreachableOrBadResponse,
      Unscheduled
   }

   //
   // IMPORTANT
   // if you change the value of these constants,
   // ensure that the clients (including the javascript client) are up to date
   //
   public static final String ARG_ANSWER_JSON_PATH = "answerjsonpath";
   public static final String ARG_BLOCK_NAMES = "blocknames";
   public static final String ARG_CONTAINER_DIR = "containerdir";
   public static final String ARG_DELTA_ENVIRONMENT_NAME = "deltaenv";
   public static final String ARG_DELTA_TESTRIG = "deltatestrig";
   public static final String ARG_DIFF_ACTIVE = "diffactive";
   public static final String ARG_ENVIRONMENT_NAME = "env";
   public static final String ARG_LOG_FILE = "logfile";
   public static final String ARG_LOG_LEVEL = "loglevel";
   public static final String ARG_OUTPUT_ENV = "outputenv";
   public static final String ARG_PEDANTIC_AS_ERROR = "pedanticerror";
   public static final String ARG_PEDANTIC_SUPPRESS = "pedanticsuppress";
   public static final String ARG_PREDICATES = "predicates";
   public static final String ARG_QUESTION_NAME = "questionname";
   public static final String ARG_RED_FLAG_AS_ERROR = "redflagerror";
   public static final String ARG_RED_FLAG_SUPPRESS = "redflagsuppress";
   public static final String ARG_SYNTHESIZE_JSON_TOPOLOGY = "synthesizejsontopology";
   public static final String ARG_SYNTHESIZE_TOPOLOGY = "synthesizetopology";
   public static final String ARG_TESTRIG = "testrig";
   public static final String ARG_UNIMPLEMENTED_AS_ERROR = "unimplementederror";
   public static final String ARG_UNIMPLEMENTED_SUPPRESS = "unimplementedsuppress";
   public static final String ARG_UNRECOGNIZED_AS_RED_FLAG = "urf";
   public static final String ARG_USE_PRECOMPUTED_ADVERTISEMENTS = "useprecomputedadvertisements";
   public static final String ARG_USE_PRECOMPUTED_IBGP_NEIGHBORS = "useprecomputedibgpneighbors";
   public static final String ARG_USE_PRECOMPUTED_ROUTES = "useprecomputedroutes";

   public static final String COMMAND_ANSWER = "answer";
   public static final String COMMAND_COMPILE_DIFF_ENVIRONMENT = "diffcompile";
   public static final String COMMAND_DUMP_DP = "dp";
   public static final String COMMAND_GET_HISTORY = "history";
   public static final String COMMAND_KEEP_BLOCKS = "keepblocks";
   public static final String COMMAND_NLS_DATA_PLANE = "nlsdp";
   public static final String COMMAND_NLS_TRAFFIC = "nlstraffic";
   public static final String COMMAND_PARSE_VENDOR_INDEPENDENT = "si";
   public static final String COMMAND_PARSE_VENDOR_SPECIFIC = "sv";
   public static final String COMMAND_QUERY = "query";
   public static final String COMMAND_REMOVE_BLOCKS = "removeblocks";
   public static final String COMMAND_WRITE_ADVERTISEMENTS = "writeadvertisements";
   public static final String COMMAND_WRITE_CP_FACTS = "cpfacts";
   public static final String COMMAND_WRITE_IBGP_NEIGHBORS = "writeibgpneighbors";
   public static final String COMMAND_WRITE_ROUTES = "writeroutes";

   public static final String KEY_BGP_ANNOUNCEMENTS = "Announcements";
   public static final String PREDICATE_FLOW_PATH_HISTORY = "FlowPathHistory";
   public static final String RELPATH_AWS_VPC_CONFIGS_DIR = "aws_vpc_configs";
   public static final String RELPATH_AWS_VPC_CONFIGS_FILE = "aws_vpc_configs";
   public static final String RELPATH_BASE = "base";
   public static final String RELPATH_CONFIG_FILE_NAME_ALLINONE = "allinone.properties";
   public static final String RELPATH_CONFIG_FILE_NAME_BATFISH = "batfish.properties";
   public static final String RELPATH_CONFIG_FILE_NAME_CLIENT = "client.properties";
   public static final String RELPATH_CONFIG_FILE_NAME_COORDINATOR = "coordinator.properties";
   public static final String RELPATH_CONFIGURATIONS_DIR = "configs";
   public static final String RELPATH_CONTROL_PLANE_FACTS_DIR = "cp_facts";
   public static final String RELPATH_CONVERT_ANSWER_PATH = "convert_answer";
   public static final String RELPATH_DATA_PLANE_DIR = "dp";
   public static final String RELPATH_DEFAULT_ENVIRONMENT_NAME = "env_default";
   public static final String RELPATH_DIFF = "diff";
   public static final String RELPATH_EDGE_BLACKLIST_FILE = "edge_blacklist";
   public static final String RELPATH_ENV_DIR = "env";
   public static final String RELPATH_ENV_NODE_SET = "env-node-set";
   public static final String RELPATH_ENVIRONMENTS_DIR = "environments";
   public static final String RELPATH_EXTERNAL_BGP_ANNOUNCEMENTS = "external_bgp_announcements.json";
   public static final String RELPATH_FAILURE_QUERY_PREFIX = "failure-query";
   public static final String RELPATH_FLOWS_DUMP_DIR = "flowdump";
   public static final String RELPATH_INTERFACE_BLACKLIST_FILE = "interface_blacklist";
   public static final String RELPATH_LB_HOSTNAME_PATH = "lb";
   public static final String RELPATH_MULTIPATH_QUERY_PREFIX = "multipath-query";
   public static final String RELPATH_NLS_INPUT_FILE = "nls_input.pl";
   public static final String RELPATH_NLS_OUTPUT_DIR = "nls_output";
   public static final String RELPATH_NODE_BLACKLIST_FILE = "node_blacklist";
   public static final String RELPATH_PARSE_ANSWER_PATH = "parse_answer";
   public static final String RELPATH_PRECOMPUTED_ROUTES = "precomputedroutes";
   public static final String RELPATH_PROTOCOL_DEPENDENCY_GRAPH = "pdgraphs";
   public static final String RELPATH_PROTOCOL_DEPENDENCY_GRAPH_ZIP = "pdgraphs.zip";
   public static final String RELPATH_QUERIES_DIR = "queries";
   public static final String RELPATH_QUESTION_FILE = "question";
   public static final String RELPATH_QUESTION_PARAM_FILE = "parameters";
   public static final String RELPATH_QUESTIONS_DIR = "questions";
   public static final String RELPATH_TEST_RIG_DIR = "testrig";
   public static final String RELPATH_TOPOLOGY_FILE = "topology";
   public static final String RELPATH_VENDOR_INDEPENDENT_CONFIG_DIR = "indep";
   public static final String RELPATH_VENDOR_SPECIFIC_CONFIG_DIR = "vendor";
   public static final String RELPATH_Z3_DATA_PLANE_FILE = "dataplane.smt2";

   public static final String SUFFIX_ANSWER_JSON_FILE = ".json";
   public static final String SUFFIX_LOG_FILE = ".log";

   public static final String SVC_BASE_RSC = "/batfishservice";
   public static final String SVC_FAILURE_KEY = "failure";
   public static final String SVC_GET_STATUS_RSC = "getstatus";
   public static final String SVC_GET_TASKSTATUS_RSC = "gettaskstatus";
   public static final Integer SVC_PORT = 9999;
   public static final String SVC_RUN_TASK_RSC = "run";
   public static final String SVC_SUCCESS_KEY = "success";
   public static final String SVC_TASK_KEY = "task";
   public static final String SVC_TASKID_KEY = "taskid";

}
