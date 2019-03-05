#verbose=true

bean.id.1=chain
chain.class=chainofresponsability.HandlerNeutre
chain.property.1=successor
chain.property.1.param.1=null


bean.id.2=a
a.class=chainofresponsability.HandlerA
a.property.1=successor
a.property.1.param.1=b

bean.id.3=b
b.class=chainofresponsability.HandlerB

