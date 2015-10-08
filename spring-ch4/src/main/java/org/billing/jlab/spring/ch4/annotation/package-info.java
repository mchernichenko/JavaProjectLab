/**
 * В пакете представлены реализации классов для конфигурирования бинов Spring в стиле Java-аннотаций
 *
 * Для определения бинов Spring через аннотации объявлять бины в app-context-annotation не требуется
 * (достаточно добавить в xml дескрипторы <context:annotation-config /> и <context:component-scan см. app-context-annotation.xml).
 *
 * Достаточно добавить соответсвующую аннотацию к классам реализации служб в пакете
 * Аннотация @Service(имя бина) используется для указания на то, что этот бин предоставляет службы,
 * которые могут понадобиться другим службам
 *
 * Во время начальной загрузки ApplicationContext c XML-конфигурацией Spring будет искать эти компоненты и создавать бины с указанными именами
 *
 * Для внедрения методом установки (для стиля аннотаций) используется @Autowired для set-метода внедрения
 *
 */
package org.billing.jlab.spring.ch4.annotation;