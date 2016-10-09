package org.billing.jlab.patterns.chainofresponsibility;

import java.io.*;

/**
 * вспомогательный класс, предназначен для генерации данных и сериализации их в файл
 */
public class DataCreator {
    public static final String DAFAUL_FILE = "data.ser";

    public static void main(String[] args) {
        String fileName;
        if (args.length == 1) {
            fileName = args[0];
        } else {
            fileName = DAFAUL_FILE;
        }
        serialize(fileName);
    }

    public static void serialize(String fileName) {
        try {
            serializeToFile(createData(), fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Serializable createData() {
        ContractImpl contract_1 = new ContractImpl("firstName_1", "lastName_1", "title_1", "organization_1");
        ContractImpl contract_2 = new ContractImpl("firstName_2", "lastName_2", "title_2", "organization_2");
        ContractImpl contract_3 = new ContractImpl("firstName_3", "lastName_3", "title_3", "organization_3");
        ContractImpl contract_4 = new ContractImpl("firstName_4", "lastName_4", "title_4", "organization_4");

        Project project = new Project("nameProject", contract_1, "datailsProject");

        Task task_1 = new Task("nameTask_1", contract_4, "datailTask_1", true, project);
        Task task_2 = new Task("nameTask_2", null, "datailTask_2", true, project);
        Task task_3 = new Task("nameTask_3", contract_3, "datailTask3", false, project);

        project.addProjectItem(task_1);
        project.addProjectItem(task_2);
        project.addProjectItem(task_3);

        Task task_4 = new Task("nameTask_4", null, "datailTask_4", false, task_1);
        Task task_5 = new Task("nameTask_5", contract_1, "datailTask_5", true, task_1);
        Task task_6 = new Task("nameTask_6", contract_1, "datailTask_6", true, task_2);
        Task task_7 = new Task("nameTask_7", null, "datailTask_7", true, task_2);
        Task task_8 = new Task("nameTask_8", null, "datailTask_8", false, task_2);
        Task task_9 = new Task("nameTask_9", null, "datailTask_9", true, task_3);
        Task task_10 = new Task("nameTask_10", contract_4, "datailTask_10", false, task_3);

        task_1.addProjectItems(task_4);
        task_1.addProjectItems(task_5);
        task_2.addProjectItems(task_6);
        task_2.addProjectItems(task_7);
        task_2.addProjectItems(task_8);
        task_3.addProjectItems(task_9);
        task_3.addProjectItems(task_10);
        return project;
    }

    private static void serializeToFile(Serializable content, String fileName) throws IOException {
        ObjectOutputStream serOut = new ObjectOutputStream(new FileOutputStream(fileName));
        serOut.writeObject(content);
        serOut.close();
    }

}
