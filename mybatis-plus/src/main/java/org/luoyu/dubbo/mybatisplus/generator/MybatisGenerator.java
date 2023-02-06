package org.luoyu.dubbo.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MybatisGenerator {
    /**
     * 代码输出目录，根据自定义
     * /apps/cores/baseservice/src/main/java
     */
    public static String outPutDir = null;
    /**
     * mapper文件输出目录
     * /apps/cores/baseservice/src/main/resources/mapper/
     */
    public static String mapperXmlDir = null;
    /**
     * jdbc:mysql://192.168.38.100:33306/dbName?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true
     */
    public static String url = null;
    /**
     * yuansu
     */
    public static String username = null;
    /**
     * yuansu123
     */
    public static String password = null;
    /**
     * 自定义指定
     * net.elementech.server.baseservice
     */
    public static String parentModel = null;



    private static String author = "LuoYu";
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String entityName = "model";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void execMain(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        TableFill createField = new TableFill("createTime", FieldFill.INSERT);
        TableFill modifiedField = new TableFill("updateTime", FieldFill.UPDATE);
        TableFill addUser = new TableFill("createrUserId", FieldFill.INSERT);
        TableFill updateUser = new TableFill("updateUserId", FieldFill.UPDATE);
        tableFillList.add(createField);
        tableFillList.add(modifiedField);
        tableFillList.add(addUser);
        tableFillList.add(updateUser);


        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 输出模块java文件路径
        gc.setOutputDir(projectPath + outPutDir);
        gc.setAuthor(author);
        gc.setActiveRecord(true); // 是否支持AR模式
        gc.setSwagger2(true); // 使用Swagger
        gc.setFileOverride(false); // 文件覆盖
        gc.setIdType(IdType.ASSIGN_ID); // 主键策略 分布式long主键生成
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setOpen(false); // 生成文件后 不打开文件夹
        gc.setBaseResultMap(true); // XML ResultMap
        gc.setBaseColumnList(true); // XML columList
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        dsc.setDriverName(driverClassName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setEntity(entityName);
        pc.setParent(parentModel);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/model/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templateCPath = "/templates/model/controller.java.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String mn = pc.getModuleName();
                String[] arr = mn.split("\\.");
                String te = "";
                for (String s: arr) {
                    te += s + "/";
                }
                return projectPath + mapperXmlDir + te +  tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/model/entity.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setController(null);
        templateConfig.setMapper("/templates/model/mapper.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass(this.baseEntityPath);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setTableFillList(tableFillList);
        // 公共父类
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");


        strategy.setVersionFieldName("versionNum"); // 数据库版本控制字段
        strategy.setLogicDeleteFieldName("isDeleted"); // 数据库逻辑删除字段
        //strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}