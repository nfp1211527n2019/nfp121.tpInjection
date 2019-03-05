
#verbose=true

bean.id.1=configGenerator1
configGenerator1.class=config_editor.ConfigGenerator
configGenerator1.property.1=beanName
configGenerator1.property.1.param.1=generator
configGenerator1.property.2=beanPrefixName
configGenerator1.property.2.param.1=test_
configGenerator1.property.3=beanNumber
configGenerator1.property.3.param.1=1
configGenerator1.property.4=formatter
configGenerator1.property.4.param.1=propertiesFormatter1
configGenerator1.property.5=beanClassFileName
configGenerator1.property.5.param.1=config_editor.ConfigGenerator


bean.id.2=propertiesFormatter1
propertiesFormatter1.class=config_editor.PropertiesFormatter

bean.id.3=configGeneratorDir
configGeneratorDir.class=config_editor.ConfigGenerator
configGeneratorDir.property.1=beanPrefixName
configGeneratorDir.property.1.param.1=pre_
configGeneratorDir.property.2=beanNumber
configGeneratorDir.property.2.param.1=5
configGeneratorDir.property.3=formatter
configGeneratorDir.property.3.param.1=propertiesFormatter2
configGeneratorDir.property.4=beanClassFileName
configGeneratorDir.property.4.param.1=config_editor/


bean.id.4=propertiesFormatter2
propertiesFormatter2.class=config_editor.PropertiesFormatter

