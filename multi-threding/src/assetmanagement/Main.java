package assetmanagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static void main() {

        ExecutorService executor =
                Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 1; i <= 10000; i++) {

            AssetData data = new AssetData(
                    "TURBINE-" + i,
                    AssetType.TURBINE,
                    Math.random() * 100,
                    Math.random() * 120
            );

            Runnable task = new AssetDataProcessor(data);
            executor.submit(task);

        }

        executor.shutdown();

    }

}
