package assetmanagement;

class AssetData {
    final String assetId;
    final AssetType type;
    final double power;
    final double temperature;

    AssetData(String assetId, AssetType type,
              double power, double temperature) {
        this.assetId = assetId;
        this.type = type;
        this.power = power;
        this.temperature = temperature;
    }
}