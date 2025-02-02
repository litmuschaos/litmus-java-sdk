package io.litmuschaos.constants;

public class OperationNames {

    // Environment
    public static final String GET_ENVIRONMENT = "getEnvironment";
    public static final String LIST_ENVIRONMENTS = "listEnvironments";
    public static final String CREATE_ENVIRONMENT = "createEnvironment";
    public static final String DELETE_ENVIRONMENT = "deleteEnvironment";
    public static final String UPDATE_ENVIRONMENT = "updateEnvironment";

    // Infrastructure
    public static final String GET_INFRA = "getInfra";
    public static final String LIST_INFRAS = "listInfras";
    public static final String GET_INFRA_DETAILS = "getInfraDetails";
    public static final String GET_INFRA_STATS = "getInfraStats";
    public static final String GET_INFRA_MANIFEST = "getInfraManifest";
    public static final String CONFIRM_INFRA_REGISTRATION = "confirmInfraRegistration";
    public static final String DELETE_INFRA = "deleteInfra";
    public static final String REGISTER_INFRA = "registerInfra";

    // ChaosHub
    public static final String LIST_CHAOS_HUB = "listChaosHub";
    public static final String GET_CHAOS_HUB = "getChaosHub";
    public static final String GET_CHAOS_HUB_STATS = "getChaosHubStats";
    public static final String ADD_CHAOS_HUB = "addChaosHub";
    public static final String ADD_REMOTE_CHAOS_HUB = "addRemoteChaosHub";
    public static final String DELETE_CHAOS_HUB = "deleteChaosHub";
    public static final String SAVE_CHAOS_HUB = "saveChaosHub";
    public static final String SYNC_CHAOS_HUB = "syncChaosHub";
    public static final String UPDATE_CHAOS_HUB = "updateChaosHub";

    // Experiment
    public static final String GET_EXPERIMENT = "getExperiment";
    public static final String LIST_EXPERIMENT = "listExperiment";
    public static final String GET_EXPERIMENT_STATS = "getExperimentStats";
    public static final String GET_PREDEFINED_EXPERIMENT = "getPredefinedExperiment";
    public static final String LIST_PREDEFINED_EXPERIMENTS = "listPredefinedExperiments";
    public static final String RUN_CHAOS_EXPERIMENT = "runChaosExperiment";
    public static final String SAVE_CHAOS_EXPERIMENT = "saveChaosExperiment";
    public static final String UPDATE_CHAOS_EXPERIMENT = "updateChaosExperiment";
    public static final String CREATE_CHAOS_EXPERIMENT = "createChaosExperiment";
    public static final String DELETE_CHAOS_EXPERIMENT = "deleteChaosExperiment";
    public static final String UPDATE_CRON_EXPERIMENT_STATE = "updateCronExperimentState";
    public static final String GET_EXPERIMENT_RUN = "getExperimentRun";
    public static final String GET_EXPERIMENT_RUN_STATS = "getExperimentRunStats";
    public static final String LIST_EXPERIMENT_RUN = "listExperimentRun";
    public static final String CHAOS_EXPERIMENT_RUN = "chaosExperimentRun";
    public static final String STOP_EXPERIMENT_RUNS = "stopExperimentRuns";

    // GitOps
    public static final String GET_GIT_OPS_DETAILS = "getGitOpsDetails";
    public static final String DISABLE_GIT_OPS = "disableGitOps";
    public static final String ENABLE_GIT_OPS = "enableGitOps";
    public static final String GITOPS_NOTIFIER = "gitopsNotifier";
    public static final String UPDATE_GIT_OPS = "updateGitOps";

    // Image Registry
    public static final String GET_IMAGE_REGISTRY = "getImageRegistry";
    public static final String LIST_IMAGE_REGISTRY = "listImageRegistry";
    public static final String CREATE_IMAGE_REGISTRY = "createImageRegistry";
    public static final String DELETE_IMAGE_REGISTRY = "deleteImageRegistry";
    public static final String UPDATE_IMAGE_REGISTRY = "updateImageRegistry";

    // Probe
    public static final String LIST_PROBES = "listProbes";
    public static final String GET_PROBE = "getProbe";
    public static final String VALIDATE_UNIQUE_PROBE = "validateUniqueProbe";
    public static final String GET_PROBE_REFERENCE = "getProbeReference";
    public static final String GET_PROBE_YAML = "getProbeYAML";
    public static final String GET_PROBES_IN_EXPERIMENT_RUN = "getProbesInExperimentRun";
    public static final String ADD_PROBE = "addProbe";
    public static final String DELETE_PROBE = "deleteProbe";
    public static final String UPDATE_PROBE = "updateProbe";

    // Chaos Fault
    public static final String GET_CHAOS_FAULT = "getChaosFault";
    public static final String LIST_CHAOS_FAULTS = "listChaosFaults";

    // Others
    public static final String GET_SERVER_VERSION = "getServerVersion";
    public static final String GET_VERSION_DETAILS = "getVersionDetails";
    public static final String GENERATE_SSH_KEY = "generateSSHKey";
    public static final String GET_MANIFEST_WITH_INFRA_ID = "getManifestWithInfraID";
}
