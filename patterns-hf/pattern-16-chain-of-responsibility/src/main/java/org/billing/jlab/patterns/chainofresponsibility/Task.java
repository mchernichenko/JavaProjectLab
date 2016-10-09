package org.billing.jlab.patterns.chainofresponsibility;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Класс Task представляет собой некоторую отдельную задачу в рамках проекта
 * Поведение, характерное для шаблона Chain of Responsibility, проявляется в методах getOwner и getDetails
 */
public class Task implements ProjectItem {
    private String name;
    private Contract owner;
    private String details;
    private boolean primaryTask;
    private ProjectItem prent;     // ссылка на родительский объект
    private ArrayList<ProjectItem> projectItems = new ArrayList<ProjectItem>(); // хранит коллекцию подзадач

    public Task(String name, Contract owner, String details, boolean primaryTask, ProjectItem prent) {
        this.name = name;
        this.owner = owner;
        this.details = details;
        this.primaryTask = primaryTask;
        this.prent = prent;
    }

    public Task(ProjectItem prent) {
        this("", null, "", false, prent);
    }

    public ProjectItem getPrent() {
        return prent;
    }

    public boolean isPrimaryTask() {
        return primaryTask;
    }

    /**
     * Данный метод вызывает метод getDetails каждого из родителей до тех пор, пока не достигнет класса
     Task или Project, идентифицированного как терминальный узел. Это означает, что метод getDetails возвращает набор объектов
     класса String, совокупность которых представляет описание конкретной задачи с глубиной детализации, соответствующей
     точке вызова getDetails.
     * @return
     */
    public String getDetails() {
        if (primaryTask) {
            return details;
        } else {
            return prent.getDetails() + EOL_STRING + "\t" + details;
        }
    }

    /**
     * возвращает, либо значение атрибута владельца своего класса (если оно отлично от нуля),
     * либо значение, предоставленное родительским классом
     * Если родительским классом является класс Task и его атрибут владельца также не установлен, вызов метода
     * передается следующему родителю до тех пор, пока не будет найдено отличное от нуля
     * значение или пока не будет вызван аналогичный метод класса Project.
     * Это позволяет без особого труда создать группу задач, представленных экземплярами класса Task,
     * владельцем которых будет назначено одно лицо, отвечающее как за исполнение главной задачи Task, так и всех ее подзадач.
     */
    public Contract getOwner() {
        if (owner == null) {
            return prent.getOwner();
        } else {
            return owner;
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<ProjectItem> getProjectItems() {
        return projectItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Contract owner) {
        this.owner = owner;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPrimaryTask(boolean primaryTask) {
        this.primaryTask = primaryTask;
    }

    public void setPrent(ProjectItem prent) {
        this.prent = prent;
    }

    public void addProjectItems(ProjectItem element) {
        if (!projectItems.contains(element)) {
            projectItems.add(element);
        }
    }

    public void ramoveProjectItems(ProjectItem element) {
        projectItems.remove(element);
    }

    public ProjectItem getParent() {
        return prent;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}

