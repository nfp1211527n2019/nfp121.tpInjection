#verbose=true

bean.id.1=context1
context1.class=strategy.Context
context1.property.1=strategy
context1.property.1.param.1=plus

bean.id.2=context2
context2.class=strategy.Context
context2.property.1=strategy
context2.property.1.param.1=moins
#context2.property.1.param.1=mul

bean.id.3=context3
context3.class=strategy.Context
context3.property.1=strategy
context3.property.1.param.1=mul

bean.id.4=plus
plus.class=strategy.OperationAdd

bean.id.5=moins
moins.class=strategy.OperationSubstract

bean.id.6=mul
mul.class=strategy.OperationMultiply

bean.id.7=context4
context4.class=strategy.Context
context4.property.1=strategy
context4.property.1.param.1=div

bean.id.8=div
div.class=strategy.OperationDiv

bean.id.9=invocateur
invocateur.class=strategy.Invocateur
invocateur.property.1=context
invocateur.property.1.param.1=context4





