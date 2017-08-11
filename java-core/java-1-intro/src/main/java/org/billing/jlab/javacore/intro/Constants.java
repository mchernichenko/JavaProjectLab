package org.billing.jlab.javacore.intro;

/**
 * Константы OAPI
 */
public class Constants {
    //********************************
    // Форматы даты
    //********************************
    public static final String DATE_FORMAT_WITH_TIME = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT_WITH_MILLISECs="yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATE_FORMAT_WITH_TZ = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DATE_FORMAT_WITH_MILLISEC_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String DATE_FORMAT_WITHOUT_TIME = "yyyy-MM-dd";
    public static final String DATE_FORMAT_WITH_MILLISEC = "yyyyMMdd'T'HHmmss.SSS";//"yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATE_FORMAT_WITHOUT_MILLISEC = "yyyyMMdd'T'HHmmss"; //"yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT_WITH_TIMEZONE="yyyy-MM-dd'T'HH:mm:ss.TZD";
    public static final String DATABASE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATABASE_DATE_FORMAT_WITHMILLISEC = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATABASE_DATE_FORMAT_WITHTIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String JODA_DATE_FORMAT_MILLISEC_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

    //********************************************
// Backend Admin credentials
//********************************************
    public static final String BACKEND_ADMIN_NAME = "adm";
    public static final String BACKEND_ADMIN_PASS = "1111admin";
    public static final String BACKEND_RELOAD_URI = "/manager/text/reload?path=";

    //********************************************
// Тайм-зона сервера
//********************************************
    public static final String SERVER_TIME_ZONE = "GMT+0";


//********************************************
// URL
//********************************************

    public static final String HTTP = "http://";
    public static final String SERVER = "srv2-x64rh6-01";
    //public static final String SERVER = "srv2-rnd-aoc02";

    public static final String FRONTEND_SERVER_URL = SERVER;
    public static final String HAS_SERVER_URL = SERVER;
    public static final String BACKEND_SERVER_URL = SERVER;

    public static final int FRONTEND_HTTP_PORT = 22022;
    public static final int BACKEND_HTTP_PORT = 18080;
    public static final int HAS_SERVER_HTTP_PORT = 7889;

    public static final String WAR_LOCATION = "src/test/webapp";
    public static final String PROJECT ="/openapi";   // настраивается в Tomcat
    public static final String VERSION = "v1";
    public static final String VERSION_URI = PROJECT + "/" + VERSION;
    public static final String FRONTEND_HTTP_ADDRESS = HTTP + FRONTEND_SERVER_URL + ":"+ FRONTEND_HTTP_PORT + VERSION_URI;

    public static final String HAS_SERVER_HTTP_ADDRESS = HTTP + HAS_SERVER_URL + ":" + HAS_SERVER_HTTP_PORT;
    public static final String BACKEND_APPLICATION_NAME = "/oapi-bis-backend-6.0";
    public static final String BACKEND_LOCATION = "/backend";
    public static final String BACKEND_HTTP_ADDRESS = HTTP + BACKEND_SERVER_URL + ":"+ BACKEND_HTTP_PORT + BACKEND_APPLICATION_NAME + BACKEND_LOCATION;

    public static final String BACKEND_RELOAD_HTTP_ADDRESS =
            HTTP + BACKEND_ADMIN_NAME + ":" + BACKEND_ADMIN_PASS + "@" + BACKEND_SERVER_URL + ":"+ BACKEND_HTTP_PORT +
                    BACKEND_RELOAD_URI + BACKEND_APPLICATION_NAME;

//********************************************
// HEADERS
//********************************************
    // HEADER NAMES
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_DATE = "Date";

    // HEADER VALUES (ограниченный набор допустимых значений)
    public static final String REQUEST_ACCEPT_DEFAULT = null;
    public static final String ACCEPT_JSON = "application/json;charset=UTF-8";
    public static final String ACCEPT_XML  = "text/xml;charset=UTF-8";
    public static final String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";

    //********************************************
// URI-path (строка с адресом сущности API)
//********************************************
    public static final String URI_PATH_GETSESSION = "/tokens-stub/get";
    //
    public static final String URI_PATH_DICTIONARIES = "/dictionaries";
    public static final String URI_PATH_COMMON = "/common";
    public static final String URI_PATH_BRANCHES = "/branches";
    public static final String URI_PATH_BILLING = "/billing";
    public static final String URI_PATH_AGENTS = "/agents";
    public static final String URI_PATH_CUSTOMERS = "/customers";
    public static final String URI_PATH_SUBSCRIBERS = "/subscribers";
    public static final String URI_PATH_ASSOCIATIONS = "/associations";
    //
//
    public static final String URI_PATH_PRODUCTS = "/products";

//********************************************
// Expected-path (путь к каталогу ожидаемых результатов от classpath)
//********************************************

    public static final String EXPECTED_DICTS_PATH = "/expected/json/dictionaries/";

//********************************************
// HAS USER NAMES (пользователи HAS для тестирования)
//********************************************

    public static final String Z_NAME_FULL = "Z_NAME_FULL";
    public static final String Z_NAME_FULL_SUBS = "Z_NAME_FULL_SUBS";
    public static final String Z_NAME_BLOCK = "Z_NAME_BLOCK";
    public static final String Z_NAME_1 = "Z_NAME_1";
    public static final String Z_NAME_LEGAL = "Z_NAME_LEGAL";
    public static final String Z_NAME_LEGAL_SHORT = "Z_NAME_LEGAL_SHORT";
    public static final String Z_NAME_NOT_EXIST = "Z_USER_NOT_EXIST";

}
