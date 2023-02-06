package ${package.Service};

<#list "${package.Service}"?split("business") as base>
import ${base}repository.model.${entity};
import ${base}repository.mapper.${entity}Mapper;
import ${base}business.model.input.${cfg.createInput!};
import ${base}business.model.input.${cfg.updateInput!};
    <#break>
</#list>
import ${cfg.iBaseServicePath};

/**
* ${(cfg.moduleName)!}业务接口
* @author ${author}
* @since ${date}
*/
public interface ${table.serviceName} extends ${cfg.iBaseService}<${entity}, ${cfg.createInput!}, ${cfg.updateInput!}, ${entity}Mapper> {
    String MODULE = "${(cfg.moduleName)!}";
}