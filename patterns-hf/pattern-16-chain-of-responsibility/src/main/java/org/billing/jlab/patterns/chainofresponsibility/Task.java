package org.billing.jlab.patterns.chainofresponsibility;

import java.util.ArrayList;

/**
 * Класс Task представляет собой некоторую отдельную задачу в рамках проекта (Project)
 * Имеет ссылку на родительский объект, который м.б. экземпляром как Task или Project
 * а также имеет список подзадач и признак терминального (последнего) таска не имеющего подтасков.
 * Поведение, характерное для шаблона Chain of Responsibility, проявляется в методах getOwner и getDetails
 */
public class Task implements ProjectItem {
    private String name;
    private Contract owner;
    private String details;
    private boolean primaryTask; // признак простейшего, терминального таска, т.е. у него уже не должно быть подтасков
    private ProjectItem parent;     // ссылка на родительский объект, который м.б. экземпляром Task или Project
    private ArrayList<ProjectItem> projectItems = new ArrayList<ProjectItem>(); // хранит коллекцию подзадач

    /**
     *
     * @param name - Имя таска
     * @param owner - владелец таска
     * @param details - детали таска
     * @param primaryTask - признак терминального таска (узла), т.е. только до этого узда будет выводится детализация
     * @param parent - ссылка на родительский объект, который м.б. экземпляром Task или Project
     *
     * Список подзадач такски формируется путём добавления её с помощью addProjectItems
     * При создании задачи она ничего не знает из каких подзачач она состоит, только знает за каким объектом он следует
     * т.е. знает её родителя
     */
    public Task(String name, Contract owner, String details, boolean primaryTask, ProjectItem parent) {
        this.name = name;
        this.owner = owner;
        this.details = details;
        this.primaryTask = primaryTask;
        this.parent = parent;
    }

    /**
     *
     * @param parent - ссылка на родителя
     */
    public Task(ProjectItem parent) {
        this("", null, "", false, parent);
    }

    public ProjectItem getParent() {
        return parent;
    }

    public boolean isPrimaryTask() {
        return primaryTask;
    }

    /**
     Данный метод вызывает метод getDetails каждого из родителей до тех пор, пока не достигнет класса
     Project или Task, идентифицированного как терминальный узел - primaryTask=true
     По сути признак терминального таска определяет на какой таске завершится вывод деталицации, где глубина детализации
     конкретной задачи определяется соответствующей точке вызова getDetails.
     * @return
     */
    public String getDetails() {
        if (primaryTask) {
            return details;
        } else {
            return parent.getDetails() + EOL_STRING + "\t" + details;
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
            return parent.getOwner();
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

    public void setParent(ProjectItem parent) {
        this.parent = parent;
    }

    /**
     * Функция добавления подзадач для таски. Дважды одну и ту же подзадачу добавить нельзя.
     * @param element - подзадача
     */
    public void addProjectItems(ProjectItem element) {
        if (!projectItems.contains(element)) {
            projectItems.add(element);
        }
    }

    public void ramoveProjectItems(ProjectItem element) {
        projectItems.remove(element);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}

