/**
 Поведенческий шаблон, предназначенный для организации в системе уровня ответственности.
 Шаблон позволяет установить, должно ли сообщение обрабатываться на том уровне, где оно было получно или же оно
 должно передаться для обработки другому объекту.
 Часто шаблон Chain of Responsibility реализуется в виде модели "родительский объект — дочерний объект" или "контейнер — содержимое".
 В соответствии с такой моделью сообщение, не обработанное дочерним объектом, пересылается родительскому объекту, затем, возможно,
 его родительскому объекту и т.д. до тех пор, пока не будет найден объект, имеющий нужный обработчик.

 В данном примере строится иерархическая структура проекта, т.е. проект (Project) может состоять из нескольких отдельных задач (Task),
 которые в свою очередь могут состоять из подзадач.

 При создании задачи (new Task) она ничего не знает из каких подзачач она состоит или будет состоять,
 только знает за каким объектом он следует т.е. знает её родителя и всё.
 Список подзадач таски, конечно, может быть сформирован путём добавления её с помощью функции addProjectItems.

 Структура проекта (см. DataCreator):
 Project  -> task_1_t -> task_4
  contract_1)         -> task_5_t (contract_1)
          -> task_2_t -> task_6_t (contract_1)
                      -> task_7_t
                      -> task_8
          -> task_3   -> task_9_t
                      -> task_10 (contract_4)

 Поведение, характерное для шаблона Chain of Responsibility, проявляется в методах getOwner и getDetails,
 где в getOwner, если для субтаска не задан владелец, то получение владельца делегируется выше стоящему в цепочке объекту (родителю)



 */
package org.billing.jlab.patterns.chainofresponsibility;