package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ${cfg.baseCreateInputPath};

/**
* 创建${(cfg.moduleName)!}实体
* @author ${author}
* @since ${date}
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description="创建${(cfg.moduleName)!}实体")
public class ${entity} extends ${cfg.baseCreateInput} {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
