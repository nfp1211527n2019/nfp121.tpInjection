#verbose=true

bean.id.1=invoker
invoker.class=command.Invoker
invoker.property.1=commande
invoker.property.1.param.1=commandes

#invoker.property.1.param.1=cmdProjecteur
#invoker.property.1.param.1=commandes

bean.id.2=lampeDeLAmphi
lampeDeLAmphi.class=command.Light

bean.id.3=cmdLampe
cmdLampe.class=command.CommandLight
cmdLampe.property.1=lampe
cmdLampe.property.1.param.1=lampeDeLAmphi

bean.id.4=projecteurDeLAmphi
projecteurDeLAmphi.class=command.Projecteur

bean.id.5=cmdProjecteur
cmdProjecteur.class=command.CommandeProjecteur
cmdProjecteur.property.1=projecteur
cmdProjecteur.property.1.param.1=projecteurDeLAmphi

bean.id.6=commandes
commandes.class=command.MacroCommand
commandes.property.1=commands
commandes.property.1.param.1=cmdProjecteur cmdProjecteur2 cmdLampe

bean.id.7=cmdProjecteur2
cmdProjecteur2.class=command.CommandeProjecteur
cmdProjecteur2.property.1=projecteur
cmdProjecteur2.property.1.param.1=projecteurDeLaSalle

bean.id.8=projecteurDeLaSalle
projecteurDeLaSalle.class=command.Projecteur

