package samaco.idepardazan.goldcommandcodes.Common;

/**
 * Created by Alireza on 08/11/2015.
 */
public class ClassHelper {

    private static String AppVersion;

    private static String DeviceMode;
    private static String MANUFACTURER;
    private static String DEVICE;
    private static String PRODUCT;
    private static String OS_version;
    private static String VERSION;
    private static String EditStatus;

    private static  String PreviousPage;
    private static String MarketName;

    private static String SimSerialNumber;
    private static String SimOperatorName;
    private static String DeviceId;
    private static  String GroupPreviousPage;
    private static  String SearchData;

    public static String getSearchData() {
        return SearchData;
    }

    public static void setSearchData(String searchData) {
        SearchData = searchData;
    }

    public static String getGroupPreviousPage() {
        return GroupPreviousPage;
    }

    public static void setGroupPreviousPage(String groupPreviousPage) {
        GroupPreviousPage = groupPreviousPage;
    }

    public static String getEditStatus() {
        return EditStatus;
    }

    public static void setEditStatus(String editStatus) {
        EditStatus = editStatus;
    }

    public static String getMarketName() {
        return MarketName;
    }

    public static void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public static String getPreviousPage() {
        return PreviousPage;
    }

    public static void setPreviousPage(String previousPage) {
        PreviousPage = previousPage;
    }

    public static String getDeviceMode() {
        return DeviceMode;
    }

    public static void setDeviceMode(String deviceMode) {
        DeviceMode = deviceMode;
    }

    public static String getMANUFACTURER() {
        return MANUFACTURER;
    }

    public static void setMANUFACTURER(String MANUFACTURER) {
        ClassHelper.MANUFACTURER = MANUFACTURER;
    }

    public static String getDEVICE() {
        return DEVICE;
    }

    public static void setDEVICE(String DEVICE) {
        ClassHelper.DEVICE = DEVICE;
    }

    public static String getPRODUCT() {
        return PRODUCT;
    }

    public static void setPRODUCT(String PRODUCT) {
        ClassHelper.PRODUCT = PRODUCT;
    }

    public static String getOS_version() {
        return OS_version;
    }

    public static void setOS_version(String OS_version) {
        ClassHelper.OS_version = OS_version;
    }

    public static String getVERSION() {
        return VERSION;
    }

    public static void setVERSION(String VERSION) {
        ClassHelper.VERSION = VERSION;
    }

    public static String getSimSerialNumber() {
        return SimSerialNumber;
    }

    public static void setSimSerialNumber(String simSerialNumber) {
        SimSerialNumber = simSerialNumber;
    }

    public static String getSimOperatorName() {
        return SimOperatorName;
    }

    public static void setSimOperatorName(String simOperatorName) {
        SimOperatorName = simOperatorName;
    }

    public static String getDeviceId() {
        return DeviceId;
    }

    public static void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }




    public static String getAppVersion() {
        return AppVersion;
    }

    public static void setAppVersion(String appVersion) {
        AppVersion = appVersion;
    }




}
