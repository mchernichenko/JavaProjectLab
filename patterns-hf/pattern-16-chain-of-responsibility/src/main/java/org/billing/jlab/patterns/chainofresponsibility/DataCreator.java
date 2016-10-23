package org.billing.jlab.patterns.chainofresponsibility;

import java.io.*;

/**
 * вспомогательный класс, предназначен для генерации данных, т.е. создания иерархической структуры проекта и сериализации их в файл
 * Структура проекта, где _t признак терминального таска определяет на какой таске завершится вывод деталицации, где глубина детализации
   конкретной задачи определяется соответствующей точке вызова getDetails.
 *
 * Например: Чтобы вывести полную детализацию определённой ветки по проекту, необходимо вызвать метод getDetail последнего таска
 * в цепочке зависимостей и при этом они не должны быть терминальными
   для Task{name='nameTask_10'} Details: dеtailsProject -> dеtailTask3 -> detailTask_10

  Структура проекта:
 * Project -> task_1_t -> task_4
 *(contract_1)         -> task_5_t (contract_1)
 *         -> task_2_t -> task_6_t (contract_1)
 *                     -> task_7_t
 *                     -> task_8
 *         -> task_3   -> task_9_t
 *                     -> task_10 (contract_4)
 *
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

    /**
     *
     * @return  - возващаем проект (Project), представляющий из себя иерархию тасков и субтасков
     */
    private static Serializable createData() {

        // определяем набор владельцев проектов или задач
        ContractImpl contract_1 = new ContractImpl("firstName_1", "lastName_1", "title_1", "organization_1");
        ContractImpl contract_2 = new ContractImpl("firstName_2", "lastName_2", "title_2", "organization_2");
        ContractImpl contract_3 = new ContractImpl("firstName_3", "lastName_3", "title_3", "organization_3");
        ContractImpl contract_4 = new ContractImpl("firstName_4", "lastName_4", "title_4", "organization_4");


        Project project = new Project("nameProject", contract_1, "detailsProject");


        Task task_1 = new Task("nameTask_1", contract_4, "detailTask_1", true, project);
        Task task_2 = new Task("nameTask_2", null, "detailTask_2", true, project);
        Task task_3 = new Task("nameTask_3", contract_3, "detailTask3", false, project);

        // основной проект состоит из 3-х подзадач
        project.addProjectItem(task_1);
        project.addProjectItem(task_2);
        project.addProjectItem(task_3);

        // определение субтасков
        Task task_4 = new Task("nameTask_4", null, "detailTask_4", false, task_1);
        Task task_5 = new Task("nameTask_5", contract_1, "detailTask_5", true, task_1);
        Task task_6 = new Task("nameTask_6", contract_1, "detailTask_6", true, task_2);
        Task task_7 = new Task("nameTask_7", null, "detailTask_7", true, task_2);
        Task task_8 = new Task("nameTask_8", null, "detailTask_8", false, task_2);
        Task task_9 = new Task("nameTask_9", null, "detailTask_9", true, task_3);
        Task task_10 = new Task("nameTask_10", contract_4, "detailTask_10", false, task_3);

        // включение субтасков в основные таски
        task_1.addProjectItems(task_4);
        task_1.addProjectItems(task_5);
        task_2.addProjectItems(task_6);
        task_2.addProjectItems(task_7);
        task_2.addProjectItems(task_8);
        task_3.addProjectItems(task_9);
        task_3.addProjectItems(task_10);

        // возващаем проект, представляющий из себя иерархию тасков
        return project;
    }

    private static void serializeToFile(Serializable content, String fileName) throws IOException {
        ObjectOutputStream serOut = new ObjectOutputStream(new FileOutputStream(fileName));
        serOut.writeObject(content);
        serOut.close();
    }
}
