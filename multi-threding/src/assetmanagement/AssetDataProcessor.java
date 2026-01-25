package assetmanagement;

class AssetDataProcessor implements Runnable {

    private final AssetData data;

    AssetDataProcessor(AssetData data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println(
            Thread.currentThread().getName() +
            " processing " + data.assetId
        );

        validate();
        analyze();
        persist();
        checkAlerts();
    }

    private void validate() {
        if (data.temperature > 100) {
            System.out.println("High temperature detected for " + data.assetId);
        }
    }

    private void analyze() {
        try { Thread.sleep(200); } catch (Exception ignored) {}
    }

    private void persist() {
        System.out.println("Persisted data for " + data.assetId);
    }

    private void checkAlerts() {
        if (data.power < 10) {
            System.out.println("Low power alert for " + data.assetId);
        }
    }
}