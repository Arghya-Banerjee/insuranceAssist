package config;

public class ApiEndpoints {

    // Base URL now includes api/v1
    public static final String BASE_URL = "http://localhost:8080/api/v1";

    // ================= Profile Controller =================
    public static final String PUT_PROFILE_UPDATE = "/private/profile/update/";
    public static final String GET_PROFILE = "/private/profile/get/";

    // ================= Policy Controller =================
    public static final String PUT_POLICY_UPDATE = "/private/policy/update/";
    public static final String POST_POLICY_CREATE = "/private/policy/create";
    public static final String GET_POLICY = "/private/policy/get/";
    public static final String GET_POLICY_TYPE = "/private/policy/get/policyType";
    public static final String GET_POLICY_TYPE_BY_CLIENT = "/private/policy/get/policyType/";
    public static final String DELETE_POLICY = "/private/policy/delete/";

    // ================= Hospital Controller =================
    public static final String PUT_HOSPITAL_UPDATE = "/private/hospital/update/";
    public static final String POST_HOSPITAL_CREATE = "/private/hospital/create";
    public static final String GET_HOSPITALS = "/private/hospital/get/";
    public static final String GET_HOSPITAL_DETAILS = "/private/hospital/get/";
    public static final String DELETE_HOSPITAL = "/private/hospital/delete/";

    // ================= Dependent Controller =================
    public static final String PUT_DEPENDENT_UPDATE = "/private/dependent/update/";
    public static final String POST_DEPENDENT_CREATE = "/private/dependent/create";
    public static final String GET_DEPENDENT_DETAILS = "/private/dependent/getDetails/";
    public static final String GET_DEPENDENTS_BY_CLIENT = "/private/dependent/get/";
    public static final String DELETE_DEPENDENT = "/private/dependent/delete/";

    // ================= Claim Controller =================
    public static final String PUT_CLAIM_UPDATE_STATUS = "/private/claim/update/";
    public static final String PUT_CLAIM_UPDATE_STATUS_AMOUNT = "/private/claim/update/";
    public static final String POST_CLAIM_CREATE = "/private/claim/create";
    public static final String GET_CLAIM = "/private/claim/get/";
    public static final String GET_CLAIMS_BY_CLIENT = "/private/claim/get/client/";
    public static final String GET_CLAIMS_BY_CLIENT_PAGED = "/private/claim/get/client/";
    public static final String GET_CLAIMS_BY_AGENT = "/private/claim/get/agent/";
    public static final String GET_CLAIMS_BY_AGENT_PAGED = "/private/claim/get/agent/";
    public static final String DELETE_CLAIM = "/private/claim/delete/";

    // ================= Registration Controller =================
    public static final String POST_REGISTER = "/public/register";

    // ================= Login Controller =================
    public static final String POST_LOGIN = "/public/login";

    // ================= Document Controller =================
    public static final String POST_DOCUMENT_UPLOAD = "/private/document/upload/";
    public static final String GET_DOCUMENT_DOWNLOAD = "/private/document/download/";

    private ApiEndpoints() {
        // prevent instantiation
    }
}
