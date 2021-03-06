/**
 1. Бины и фабрики бинов (spring.ch4.beanfactory) - интерфейс BeanFactory
    Бин - в Spring это ссылка на любой компонент, управляемый контейнером (по сути экземпляр объекта)
    Ядром контейнера DI в Spring является интерфейс фабрики бинов BeanFactory, который управляет компонентами, их зависимостями и ЖЦ

 2. ApplicationContext как расширение BeanFactory.
    ВСЕГДА пользуем его, а BeanFactory как для примера, т.к. предоставляет намного больше вариантов конфигурации чем традиционный BeanFactory.
    Т.е. в дополнение к DI предоставляет предоставляет другие службы: служба транзакций, служба АОП,обработка событий приложения и многое другое.
    При взаимодействии со Spring лучше ВСЕГДА пользовать этот интерфейс.

    В DeclareSpringComponents рассмотрен пример конфигурации Spring в стиле XML и аннотаций и использование внедрения через метод установки (Setter Injection)

 Варианты конфигурации Spring (определения бинов):
 - XML-файлом - позволяет вынести всю конфигурацию за пределы Java-кода
 - Java-аннотациями (app-context-annotation) - дают возможность разработчику определять и просматривать настройку DI внутри кода
 Spring поддерживает смесь этих подходов в одном ApplicationContext

 Часто применяется подход с определением инфраструктуры приложения в XML(источник данных, диспетчер транзакций, фабрики подключений JMS,JMX),
 к конфигурация DI (внедряемых бинов и зависмостей для бинов) в аннотациях.
 (!) Но нужно определиться со стилем и следовать ему для упрощения разработки и сопровождения

 Вариант конфигурации Spring никак не влияет на способ палучения бинов из ApplicationContext

  3. Использование внедрения через конструктор
     В  том же DeclareSpringComponents рассмотрен пример получения бина configurableMessageRenderer, который конфигуриться в конструкторе

  4. Параметры внедрения простых типов рассмотрены в InjectSimple.

  5. Внедрение значений с использованием SpEL (Spring Expression Language)
     Например, если заранее мы не знаем параметры конструирования объекта. Тогда для динамического конструирования объектов
     внедряемые параметры выносятся в конфигурационый класс (InjectSimpleConfig), а затем применяются в ApplicationContext для внедрения в бины (см. пример InjectSimpleSpel)

  6. Иерархия ApplicationContext рассматривается в пакете hierarchical.
     Суть чтобы вложить один контекст в другой необходимо вызвать метод setParent в дочернем контексте
     Это позволяет разделять конфигурацию на отдельные файлы. Удобно для крупных проектов, содержащих множество бинов.
     В дескрипторе ref можно ссылаться на бины как в дочернем так и в родительском контекстах. Это позволяет перемещать бины между конфигурационными файлами по мере роста приложения

  7. Внедрение коллекций (см. CollectionInjection).
     Для доступа к коллекциям бинам, а не только к отдельным бинам или значениям

  8. Режим создания экзампляров бинов (одиночные/не одиночные)
     Не путать одиночный объект - имеющий единственный экзампляр в приложении и шаблон проектирования Singleton
     Singleton увеличивает степень связанности, т.е. код приложения должен всегда явно знать о классе Singleton, чтобы получить его экземпляр и это затрудняет замену реализаций произвольным образом
     По умолчанию Spring НЕ создает новый экзампляр бина каждый раз, когда он запрашивается приложением
     А установка область действия бина на уровне прототипа заставляет Spring создавать новый экзампляр бина каждый раз, когда он запрашивается приложением


 */
package org.billing.jlab.spring.ch4;