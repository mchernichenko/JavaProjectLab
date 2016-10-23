package org.billing.jlab.patterns.chainofresponsibility;

import java.util.ArrayList;

/**
 * Класс Project является базой проекта с иерархической структурой, который
 * может состоять из нескольких отдельных задач (Task), которые в свою очередь могут состоять из подзадач
 * Родителей не имееет, а подзадачи в проект, определяющие его структуро добавляются спомощью addProjectItem
 */
public class Project implements ProjectItem {
    private String name;
    private Contract owner;
    private String details;

    // список нижележащих элементов проекта
    private ArrayList<ProjectItem> projectItems = new ArrayList();

    public Project(String name, Contract owner, String details) {
        this.name = name;
        this.owner = owner;
        this.details = details;
    }

    /**
     * Класс Project является базой проекта, поэтому метод возвращает пустой указатель
     * @return
     */
    public ProjectItem getParent() {
        return null;
    }

    public String getName() {
        return name;
    }

    public Contract getOwner() {
        return owner;
    }

    public String getDetails() {
        return details;
    }

    /**
     * Возвращат ссылки на все элементы проекта нижележащего уровня
     * @return
     */
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

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }

    public void addProjectItem(ProjectItem element) {
        if (!projectItems.contains(element)) {
            projectItems.add(element);
        }
    }

    public void remaveProjectItem(ProjectItem element) {
        projectItems.remove(element);
    }
}
