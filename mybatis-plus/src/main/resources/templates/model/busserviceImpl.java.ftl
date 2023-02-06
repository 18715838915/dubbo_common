package ${package.ServiceImpl};
<#list "${package.Service}"?split("business") as base>
import ${base}repository.model.${entity};
import ${base}repository.service.I${entity}Service;
import ${base}repository.mapper.${entity}Mapper;
import ${base}business.model.input.${cfg.createInput!};
import ${base}business.model.input.${cfg.updateInput!};
    <#break>
</#list>
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import lombok.extern.slf4j.Slf4j;
import ${cfg.baseServiceImplPath};

/**
* ${(cfg.moduleName)!}接口实现
* @author ${author}
* @since ${date}
*/
@Service
@Primary
@Slf4j
public class ${table.serviceImplName} extends ${cfg.baseServiceImpl}<${entity}, ${cfg.createInput!}, ${cfg.updateInput!}, ${entity}Mapper> implements ${table.serviceName} {
    public ${table.serviceImplName}(I${entity}Service ${entity?uncap_first}ServiceImpl, ${entity}Mapper ${entity?uncap_first}Mapper) {
       super(${entity?uncap_first}ServiceImpl, ${entity?uncap_first}Mapper, ${entity}.class, ${table.serviceName}.MODULE);
    }
}