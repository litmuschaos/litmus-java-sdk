package io.litmuschaos.constants;

public class ApiEndpoints {

    // Auth
    public static final String AUTH = "/auth";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";

    // Graphql
    public static final String API_QUERY = "/api/query";

    // Token
    public static final String GET_TOKENS = "/token";
    public static final String CREATE_TOKEN = "/create_token";
    public static final String REMOVE_TOKEN = "/remove_token";

    // User
    public static final String GET_USER = "/get_user";
    public static final String GET_USERS = "/users";
    public static final String CREATE_USER = "/create_user";
    public static final String UPDATE_PASSWORD = "/update/password";
    public static final String RESET_PASSWORD = "/reset/password";
    public static final String UPDATE_USER_DETAILS = "/update/details";
    public static final String UPDATE_USER_STATE = "/update/state";

    // Capabilities
    public static final String CAPABILITIES = "/capabilities";

    // Project
    public static final String LIST_PROJECTS = "/list_projects";
    public static final String CREATE_PROJECT = "/create_project";
    public static final String UPDATE_PROJECT_NAME = "/update_project_name";
    public static final String GET_PROJECT = "/get_project";
    public static final String GET_OWNER_PROJECTS = "/get_owner_projects";
    public static final String LEAVE_PROJECT = "/leave_project";
    public static final String GET_PROJECT_ROLE = "/get_project_role";
    public static final String GET_USER_WITH_PROJECT = "/get_user_with_project";
    public static final String GET_PROJECTS_STATS = "/get_projects_stats";
    public static final String GET_PROJECT_MEMBERS = "/get_project_members";
    public static final String GET_PROJECT_OWNERS = "/get_project_owners";
    public static final String DELETE_PROJECT = "/delete_project";
}
