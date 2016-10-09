Swagger 使用入门
=====
本文档主要对使用过程的要点进行备记。

--------------------------

# Swagger Configrue
```java
package com.emcc.changedig.system;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
public class SwaggerConfig  {
    @Bean
    public Docket allDocket(){
    	Docket docket = new Docket(DocumentationType.SWAGGER_2)
    	.apiInfo(getApiInfo())// api 描述信息
    	.groupName("all")// endpoint是按照字母顺序排列的
    	.select()
    	  .apis(RequestHandlerSelectors.any())// 描述对那些接口起作用
    	  .paths(PathSelectors.any())// 通过path分组
    	.build();
    	return docket;
    }
    @Bean
    public Docket customDocket(){
       Docket docket = new Docket(DocumentationType.SWAGGER_2)
       		   .apiInfo(getApiInfo())
       		   .groupName("user")
               .select()
               	.apis(RequestHandlerSelectors.any())
               	.paths(or(regex("/user/.*"),regex("/exuser/.*")))
               .build();
       return docket;
    }
    private ApiInfo getApiInfo(){
    	ApiInfoBuilder builder = new ApiInfoBuilder();
    	return builder
    			.title("CHANGEDIG 用户中心")
    			.description("CHANGEDIG 用户中心项目API接口文档")
    			.version("v1.0.0")
    			.license("")
    			.licenseUrl("").build();
    }
}
```
# 注解Annotation
## 1. @Api
作用在类上。
```java
@Api(tags= {"用户管理"})
@Path("/users")
public class UsersController {
    ...
}
```
**描述**

 - tags: Api 标签，可以多个。列表展示时，以tag进行分组，相同标签的显示为一组。
 
## 2. @ApiOperation
方法注解
```
@ApiOperation(value = "用户名校验", notes = "用来检验用户名密码",produces = "application/json")
@RequestMapping(value = "/validate", method = { RequestMethod.POST })
public @ResponseBody BaseResponse validate(
		@ApiParam(value = "登录名", required = true,defaultValue="admin") @RequestParam("loginName") String loginName,
		@ApiParam(value = "密码", required = true)@RequestParam("password") String password) {
			....
}
```
## 3. @ApiParam | @ApiImplicitParams
方法参数注解。对于Controller的请求参数，常见方式有两种，一种是通过方法参数的形式，另一种可以通过request对象中获取。
对于前者，swagger可以自动识别为接口参数，并生成对应接口。而通过request方式的请求参数，无法自动识别。可以通过手动添加注解的方式予以解决
```java
public @ResponseBody BaseResponse validate(
		@ApiParam(value = "登录名", required = true,defaultValue="admin") @RequestParam("loginName") String loginName,
		@ApiParam(value = "密码", required = true)@RequestParam("password") String password) {
			....
}
```
和
```java
@ApiImplicitParams(
		    {
		        @ApiImplicitParam(name = "name", value="应用名称", dataType = "String", required = true, paramType = "query"),
		        @ApiImplicitParam(name = "url", value="路径", dataType = "String", required = true, paramType = "query"),
		        @ApiImplicitParam(name = "description", value="描述", dataType = "String", required = true, paramType = "query"),
		        @ApiImplicitParam(name = "order", value="顺序", dataType = "String", required = true, paramType = "query"),
		        @ApiImplicitParam(name = "searchUrl", value="搜索路径", dataType = "String", required = true, paramType = "query")
		    }
	)
	@RequestMapping(value = "/addApp", method = { RequestMethod.POST })
	public @ResponseBody BaseResponse addApp(HttpServletRequest request) {
	。。。
}
```
## 4.@ApiResponses | @ApiResponse
方法响应
```
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = User.class),
    @ApiResponse(code = 404, message = "User with given username does not exist"),
    @ApiResponse(code = 500, message = "Internal server error")}
)
```
## 5.@ApiModel |@Api
实体类及属性
```JAVA
@ApiModel(value = "用户类") 
public class User {	
	@ApiModelProperty(value="用户编码")
	private String code;
	@ApiModelProperty(value="用户名称")
	private String name;
	@ApiModelProperty(value="登录名", required = true)
	private String loginName; 
	...
}
```

## 其他

 - 如何忽略某个Controller
在创建group的时候，api的只选择有api注解的controller，想忽略某个controller的时候，直接取出其api注解。
```
 Docket docket = new Docket(DocumentationType.SWAGGER_2)
       		   .apiInfo(getApiInfo())
       		   .groupName("user")
               .select()
               	.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
               	.paths(or(regex("/user/.*"),regex("/exuser/.*")))
               .build();
```
 - 如何忽略某个方法
```
@ApiIgnore //使用这个注解忽略方法
```
 - 忽略实体属性
```
@JsonIgnore//使用这个注解忽略实体属性
```
 - 实体传参时，只要求传入某些属性，如何做？
```java
@ApiOperation(value = "新增用户", 
			notes = "增加用户。"
			+ "<br>1.groupId 必填"
			+ "<br>2.roleIds 角色必填")
```