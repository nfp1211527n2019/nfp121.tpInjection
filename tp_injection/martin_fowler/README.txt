## le fichier de configuration spring.xml
#<beans>
#    <bean id="MovieLister" class="MovieLister">
#        <property name="finder">
#            <ref local="MovieFinder"/>
#        </property>
#    </bean>
#    <bean id="MovieFinder" class="ColonMovieFinder">
#        <property name="filename">
#            <value>movies1.txt</value>
#        </property>
#    </bean>
#</beans>

#verbose=true

# MovieLister est l'identifiant du bean
bean.id.1=movieLister
# � quelle classe java ce bean est-il associ� ?
movieLister.class=martin_fowler.MovieLister

# Quelle propri�t� est � affecter, ici une seule
movieLister.property.1=finder
# Le mutateur a un seul param�tre
movieLister.property.1.param.1=movieFinder

bean.id.2=movieFinder
movieFinder.class=martin_fowler.SemiColonMovieFinder
movieFinder.property.1=filename
movieFinder.property.1.param.1=movies1.txt