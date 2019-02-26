#verbose=true

bean.id.1=texte
texte.class=decorator.Texte
texte.property.1=texte
texte.property.1.param.1=Exemple

bean.id.2=i
i.class=decorator.I
i.property.1=unTexte
i.property.1.param.1=texte

bean.id.3=b
b.class=decorator.B
b.property.1=unTexte
b.property.1.param.1=i

bean.id.4=u
u.class=decorator.U
u.property.1=unTexte
u.property.1.param.1=b

bean.id.5=texteDecore
texteDecore.class=decorator.U
texteDecore.property.1=unTexte
texteDecore.property.1.param.1=b



