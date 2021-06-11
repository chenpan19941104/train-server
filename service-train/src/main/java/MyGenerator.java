import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sanzhisoft.base.mybatis.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by syetem on 2017/2/23.
 */
public class MyGenerator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        gc.setOutputDir("D:\\devData\\IdeaProjects\\skeleton\\src\\main\\java");
        String classPath = MyGenerator.class.getResource("").getPath();
        String currentPath = classPath.substring(1).replace("target/classes", "src/main/java").replace("/", "\\");
        gc.setOutputDir(currentPath);
        gc.setFileOverride(false);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("syetem");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setTypeConvert(new MySqlTypeConvert(){
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                return super.processTypeConvert(fieldType);
//            }
//        });
        dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dsc.setUsername("sa");
        dsc.setPassword("P@ssw0rd");
        dsc.setUrl("jdbc:sqlserver://124.70.215.191:1433;DatabaseName=3d_train");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"t_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        strategy.setNaming(NamingStrategy.removePrefixAndCamel());// 表名生成策略
        strategy.setInclude(new String[] {"CARMERA_INFO","TRAIN_INFO","TrainOutput","view_pp_timezone"}); // 需要生成的表
        //strategy.setExclude(new String[]{"t_user", "t_role", "t_permission"}); // 排除生成的表
        //
//        strategy.setFieldNaming(NamingStrategy.underline_to_camel);
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.fcs.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.fcs.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.fcs.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.fcs.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.fcs.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.sanzhisoft.mybatis");
        pc.setModuleName("train");
//        pc.setController("controller");

        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
//            }
//        };
//        // 自定义 xxList.jsp 生成
//        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
//        focList.add(new FileOutConfig("/template/list.jsp.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return "D://my_" + tableInfo.getEntityName() + ".jsp";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // mpg.setTemplate(tc);
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();

//        String packageName = pc.getParent() + "." + pc.getModuleName();
        String packageName = pc.getParent();
        generate(packageName, currentPath  + packageName.replace(".", "\\"));
    }


    private static void generate(String packageName, String parentPath) {
        String domainPackage = packageName.concat(".entity");
        List<Class<?>> list = getClasssFromPackage(domainPackage);
        String constantsName = "ApiConstants"; //根据项目不同需要修改
        if (list != null) {
            String consFilePath = parentPath + "\\constants\\" + constantsName + ".java";
            String content = readToString(consFilePath);
            String info = "";
            for (Class clazz : list) {
                String thisFile = parentPath + "\\entity\\" + clazz.getSimpleName() + ".java";
                String text = readToString(thisFile);
                String p = "";
                try {
                    Document document = Jsoup.parse("<html><body>" + text + "</body></html>");
                    p = document.getElementsByTag("p").get(0).text().replace("*", "").replace(" ", "");
                } catch (Exception ex) {

                }
                if (StringUtils.isEmpty(p)) p = clazz.getSimpleName();
//                //获取主键
                Field primaryKey = getPrimaryKeyName(clazz);
                //生成API
                String name = humpToLine(toLowerCaseFirstOne(clazz.getSimpleName())).toUpperCase();
                if (!content.contains(" " + name + " =")) {
                    info += "    public static final String " + name + " = \"" + p.replace("表", "") + "接口" + "\";\r\n";
                }
                ApiModelProperty modelProperty = primaryKey.getAnnotation(ApiModelProperty.class);
                String apiDesc = StringUtils.isEmpty(p) ? clazz.getSimpleName() : p.replace("表", "");
                String idDesc = modelProperty == null || modelProperty.value() == null ? "主键" + primaryKey.getName() : modelProperty.value();
                generateApi(parentPath, clazz.getSimpleName(), primaryKey.getType().getSimpleName(), primaryKey.getName(), packageName, constantsName, apiDesc, idDesc);

            }
            if (info != "") {
                //生成api模块信息
                content = content.replace("}", info + "}");
                createFile(content, consFilePath, true);
            }
        }
    }

    public static void generateApi(String parentPath, String className, String idType, String idName, String parentPackage, String constantsName, String apiDesc, String idDesc) {
        String idNameUp = toUpperCaseFirstOne(idName);
        String classNameLow = toLowerCaseFirstOne(className);
        String apiName = humpToLine(classNameLow).toUpperCase();
        List<String> tempList = new ArrayList<>();
        tempList.add("package " + parentPackage + ".web;");
        tempList.add("");
        tempList.add("import com.sanzhisoft.base.mybatis.jersey.IResponse;");
        tempList.add("import com.sanzhisoft.base.mybatis.jersey.UserRolePrincipal;");
        tempList.add("import com.sanzhisoft.base.mybatis.jersey.auth.ISecurityContext;");
        tempList.add("import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;");
        tempList.add("import com.baomidou.mybatisplus.core.metadata.IPage;");
        tempList.add("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;");
        tempList.add("import com.sanzhisoft.base.mybatis.utils.StringUtils;");
        tempList.add("import " + parentPackage + ".constants." + constantsName + ";");
        tempList.add("import " + parentPackage + ".constants.UserRoleConstants;");
        tempList.add("import " + parentPackage + ".entity." + className + ";");
        tempList.add("import " + parentPackage + ".service.I" + className + "Service;");
        tempList.add("import io.swagger.annotations.Api;");
        tempList.add("import io.swagger.annotations.ApiOperation;");
        tempList.add("import io.swagger.annotations.ApiParam;");
        tempList.add("import org.springframework.beans.factory.annotation.Autowired;");
        tempList.add("import org.springframework.stereotype.Component;");
        tempList.add("");
        tempList.add("import javax.annotation.security.RolesAllowed;");
        tempList.add("import javax.ws.rs.*;");
        tempList.add("import javax.ws.rs.core.Context;");
        tempList.add("import javax.ws.rs.core.MediaType;");
        tempList.add("import javax.ws.rs.core.Response;");
        tempList.add("import javax.ws.rs.core.SecurityContext;");
        tempList.add("");
        tempList.add("@Path(\"" + humpToLine(classNameLow).toLowerCase().replace("_", "/") + "\")");
        tempList.add("@Component");
        tempList.add("@Produces(MediaType.APPLICATION_JSON)");
        tempList.add("@Api(value = " + constantsName + "." + apiName + ", tags = {" + constantsName + "." + apiName + "})");
        tempList.add("public class " + className + "Api implements ISecurityContext, IResponse {");
        tempList.add("    @Autowired");
        tempList.add("    I" + className + "Service " + classNameLow + "Service;");
        tempList.add("");
        tempList.add("    @POST");
        tempList.add("    @Path(\"\")");
        tempList.add("    @RolesAllowed({UserRoleConstants.ADMIN})");
        tempList.add("    @ApiOperation(value = \"新增或修改" + apiDesc + "\", tags = {" + constantsName + ".ADMIN,"+ constantsName + "." + apiName+"}, response = " + className + ".class)");
        tempList.add("    @Consumes(MediaType.APPLICATION_JSON)");
        tempList.add("    public Response addOrUpdate" + className + "(" + className + " " + classNameLow + ", @Context SecurityContext securityContext) {");
        tempList.add("        UserRolePrincipal userRolePrincipal = (UserRolePrincipal) securityContext.getUserPrincipal();");
        tempList.add("        return ok(" + classNameLow + "Service.saveOrUpdate(" + classNameLow + "));");
        tempList.add("    }");
        tempList.add("");
        tempList.add("    @GET");
        tempList.add("    @Path(\"{" + idName + "}\")");
        tempList.add("    @ApiOperation(value = \"根据" + idName + "查询" + apiDesc + "\", tags = {" + constantsName + ".ADMIN,"+ constantsName + "." + apiName+"}, response = " + className + ".class)");
        tempList.add("    public Response get" + className + "By" + idNameUp + "(@ApiParam(value = \"" + idDesc + "\") @PathParam(\"" + idName + "\") " + idType + " " + idName + ") {");
        tempList.add("        return ok(" + classNameLow + "Service.getById(" + idName + "));");
        tempList.add("    }");
        tempList.add("");
        tempList.add("");
        tempList.add("    @POST");
        tempList.add("    @Path(\"{" + idName + "}\")");
        tempList.add("    @RolesAllowed({UserRoleConstants.ADMIN})");
        tempList.add("    @ApiOperation(value = \"根据" + idName + "删除" + apiDesc + "\", tags = {" + constantsName + ".ADMIN,"+ constantsName + "." + apiName+"})");
        tempList.add("    public Response delete" + className + "By" + idNameUp + "(@ApiParam(value = \"" + idDesc + "\") @PathParam(\"" + idName + "\") " + idType + " " + idName + ") {");
        tempList.add("        " + classNameLow + "Service.removeById(" + idName + ");");
        tempList.add("        return ok();");
        tempList.add("    }");
        tempList.add("");
        tempList.add("    @GET");
        tempList.add("    @Path(\"\")");
        tempList.add("    @RolesAllowed({UserRoleConstants.ADMIN})");
        tempList.add("    @ApiOperation(value = \"查询" + apiDesc + "列表（分页）\", tags = {" + constantsName + ".ADMIN,"+ constantsName + "." + apiName+"}, response = " + className + ".class, responseContainer = \"LIST\")");
        tempList.add("    public Response get" + className + "PageList(");
        tempList.add("            @ApiParam(value = \"页码,默认1首页\") @QueryParam(\"pageIndex\") Integer pageIndex,");
        tempList.add("            @ApiParam(value = \"每页的数据数量,默认10\") @QueryParam(\"pageSize\") Integer pageSize,");
        tempList.add("            @ApiParam(value = \"关键字\") @QueryParam(\"keywords\") String keywords");
        tempList.add("    ) {");
        tempList.add("        if (pageIndex == null || pageIndex < 1) pageIndex = 1;");
        tempList.add("        if (pageSize == null || pageSize < 1) pageSize = 10;");
        tempList.add("        IPage<"+ className +"> page = new Page<>(pageIndex,pageSize);");
        tempList.add("        QueryWrapper<"+ className +"> queryWrapper = new QueryWrapper<>();");
//        tempList.add("        if(!StringUtils.isEmpty(keywords)){");
//        tempList.add("            queryWrapper.like(\"name\",keywords);");
//        tempList.add("        }");
        tempList.add("        return ok(" + classNameLow + "Service.page(page,queryWrapper));");
        tempList.add("    }");
        tempList.add("}");

        createFile(tempList, parentPath + "\\web\\" + className + "Api.java");

    }

    public static void createFile(List<String> list, String filePath) {
        Stream<String> stream = Stream.of(list.toArray(new String[list.size()]));
        String content = stream.collect(Collectors.joining("\r\n", "", ""));
        createFile(content, filePath, false);

//        log.info(filePath);
    }

    public static Field getPrimaryKeyName(Class clazz) {
        // 获取该类的所有字段
        List<Field> fields = getAllFieldsWithRoot(clazz);
        for (Field field : fields) {
            TableId id = field.getAnnotation(TableId.class);
            // 如果该字段是主键
            if (id != null) {
                // 从@Column中的name字段中获取数据表的主键字段名
//                Column column = field.getAnnotation(Column.class);
//                String idName = column.name();
//                // 将带下划线的字段名变成驼峰命名格式
//                if (idName.contains("_")) {
//                    StringBuilder sb = new StringBuilder();
//                    String[] strs = idName.split("_");
//                    sb.append(strs[0]);
//                    for (int i = 1; i < strs.length; ++i) {
//                        sb.append(strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1));
//                    }
//                    idName = sb.toString();
//                }
//                String idName = field.getName();
//                PrimaryKey primaryKey = new PrimaryKey();
//                primaryKey.setName(idName);
//                primaryKey.setTypeName(field.getType().getn.getTypeName());

                return field;
            }
        }

        return null;
    }

    //获取类clazz的所有Field，包括其父类的Field
    private static List<Field> getAllFieldsWithRoot(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        Field[] dFields = clazz.getDeclaredFields();//获取本类所有字段
        if (null != dFields && dFields.length > 0)
            fieldList.addAll(Arrays.asList(dFields));

        // 若父类是Object，则直接返回当前Field列表
        Class<?> superClass = clazz.getSuperclass();
        if (superClass == Object.class) return Arrays.asList(dFields);

        // 递归查询父类的field列表
        List<Field> superFields = getAllFieldsWithRoot(superClass);

        if (null != superFields && !superFields.isEmpty()) {
            superFields.stream().
                    filter(field -> !fieldList.contains(field)).//不重复字段
                    forEach(field -> fieldList.add(field));
        }
        return fieldList;
    }

    /**
     * 获得包下面的所有的class
     *
     * @param
     * @return List包含所有class的实例
     */

    public static List<Class<?>> getClasssFromPackage(String packageName) {
        List<Class<?>> clazzs = new ArrayList<>();
        // 是否循环搜索子包
        boolean recursive = true;
        // 包名对应的路径名称
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;

        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {

                URL url = dirs.nextElement();
                String protocol = url.getProtocol();

                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(packageName, filePath, recursive, clazzs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazzs;
    }

    /**
     * 在package对应的路径下找到所有的class
     */
    public static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive,
                                                List<Class<?>> clazzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(new FileFilter() {

            public boolean accept(File file) {
                boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
                boolean acceptClass = file.getName().endsWith("class");// 接受class文件
                return acceptDir || acceptClass;
            }
        });

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createFile(String content, String filePath, boolean isOver) {
        try {
            File file = new File(filePath);
            //如果文件不存在，创建一个文件
            if (!isOver && file.exists()) return;
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                bw.write(content);
            } catch (IOException e) {

            } finally {
                bw.close();
                fw.close();
            }
        } catch (Exception ex) {
//            log.error(ex.getMessage());
        }
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
