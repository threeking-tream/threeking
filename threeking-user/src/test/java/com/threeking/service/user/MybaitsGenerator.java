package com.threeking.service.user;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;

/**
 * @Author: A.H
 * @Date: 2020/10/20 10:07
 */
public class MybaitsGenerator {

    static String projectPath = System.getProperty("user.dir");
    /**
     * 设置全局配置
     * @return
     */
   static GlobalConfig initGlobalConfig() {

       GlobalConfig gc = new GlobalConfig();
       //当前项目路径

       gc.setOutputDir(projectPath + "/threeking-user/src/main/java");
       gc.setAuthor("ah");
       gc.setOpen(false);
       gc.setSwagger2(true);
       //是否覆盖 一般选择false
       gc.setFileOverride(false);

       gc.setDateType(DateType.ONLY_DATE);


       return gc;
   }

    /***
     * 设置数据源
     * @return
     */
    static DataSourceConfig initDataSourceConfig(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/tk_user?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dsc.setUsername("root");
        dsc.setPassword("123456");

        return dsc;
    }

    /**
     * 包配置
     * @return
     */
    static PackageConfig iniPackageConfig(){
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.threeking.service");
        pc.setModuleName("user");
        //controller设置为空，则不生成controller
        //pc.setController("");
        pc.setEntity("entity");
        return pc;
    }

    /**
     * 配置生成策略
     * @return
     */
    static StrategyConfig initStrategyConfig(){
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("user_info");
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        //sc.setEntityBuilderModel(true);
        sc.setChainModel(true);

        //逻辑删除
        /**
         * 修改1 为有效，0为无效
         * mybatis-plus:
         *   global-config:
         *     db-config:
         *       logic-delete-value: 0 #逻辑已删除值（默认为1）
         *       logic-not-delete-value: 1 #逻辑已删除值（默认为0）
         */
        //sc.setLogicDeleteFieldName("data_status");
        sc.setRestControllerStyle(false);
        return sc;
    }

    /**
     * 自定义配置
     * @return
     */
    static InjectionConfig initInjectionConfig(String moduleName){
        InjectionConfig cfg = new InjectionConfig(){
            @Override
            public void initMap() {
                // to do nothing
            }
        };
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//        // 自定义输出配置
//        ArrayList<FileOutConfig> foclist = new ArrayList<>();
//
//        foclist.add(new FileOutConfig() {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + moduleName
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });


        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {

                // 如果是Entity类型，直接放过输出文件
                if(fileType == FileType.ENTITY){
                    return true;
                }
                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
//                if (fileType == FileType.MAPPER) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
                boolean exist = new File(filePath).exists();

                //文件不存在或者全局配置的fileOverride为true才写文件
                return !exist || configBuilder.getGlobalConfig().isFileOverride();

            }
        });


        return cfg;
    }

    /**
     * 模板配置
     * @return
     */
    static TemplateConfig initTemplateConfig(){
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("");
        return templateConfig;
    }

    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //配置策略
        //1. 全局配置

        mpg.setGlobalConfig(initGlobalConfig());

        //2. 数据源
        mpg.setDataSource(initDataSourceConfig());
        //3. 包配置
        PackageConfig pc = iniPackageConfig();
        mpg.setPackageInfo(pc);

        //4. 自定义配置 还没弄明白
        mpg.setCfg(initInjectionConfig(pc.getModuleName()));
        //5. 配置模板
        mpg.setTemplate(initTemplateConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //6. 策略配置
        mpg.setStrategy(initStrategyConfig());


        //执行
        mpg.execute();
    }
}
