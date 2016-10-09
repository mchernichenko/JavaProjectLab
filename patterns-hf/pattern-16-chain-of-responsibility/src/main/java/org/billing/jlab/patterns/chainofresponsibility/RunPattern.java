package org.billing.jlab.patterns.chainofresponsibility;

import java.io.File;
import java.util.Iterator;

/**
 * координирует работу остальных классов: извлекает проект из файла, затем получает владельца
 и детали по каждой задаче и всему проекту в целом
 */
public class RunPattern {
    public static void main(String[] args) {
        if (!(new File("data.ser").exists())) {
            DataCreator.serialize("data.ser");
        }
        Project project = (Project) DataRetriever.deserializeData("data.ser");
        System.out.println("Получение Owner и детали для каждого элемента проекта");
        System.out.println();
        getitemInfo(project);
    }

    private static void getitemInfo(ProjectItem item) {
        System.out.println("ProjectItem: " + item);
        System.out.println("Owner: " + item.getOwner());
        System.out.println("Details: " + item.getDetails());
        System.out.println();
        if (item.getProjectItems() != null) {
            Iterator iterator = item.getProjectItems().iterator();
            while (iterator.hasNext()) {
                getitemInfo((ProjectItem) iterator.next());
            }
        }
    }
}
