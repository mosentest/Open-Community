<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp">
  <jsp:param name="navigation" value="home" />
</jsp:include>
<section id="content" role="main" style="background: #f5f5f5;">
<jsp:include page="edit.jsp"></jsp:include>
  <header class="aui-page-header">
    <div class="aui-page-header">
      <div class="aui-page-header-inner">
        <h1>index</h1>
      </div>
    </div>
  </header>
  <div class="aui-page-panel">
    <div class="aui-page-panel-inner">
      <div class="aui-page-panel-nav">
        <!-- Vertical Nav is usually placed inside .aui-page-panel-nav. Refer to content layout documentation for details. -->
        <nav class="aui-navgroup aui-navgroup-vertical">
          <div class="aui-navgroup-inner">
            <ul class="aui-nav">
              <li class="aui-nav-selected"><a href="http://example.com/">Usage</a></li>
            </ul>
            <div class="aui-nav-heading">
              <strong>Accounts</strong>
            </div>
            <ul class="aui-nav">
              <li><a href="http://example.com/">Users</a></li>
              <li><a href="http://example.com/">Groups</a></li>
              <li><a href="http://example.com/">Global permissions</a></li>
              <li><a href="http://example.com/">Authentication</a></li>
              <li><a href="http://example.com/">Avatars</a></li>
              <li><a href="http://example.com/">User directories</a></li>
            </ul>
            <div class="aui-nav-heading">
              <strong>Settings</strong>
            </div>
            <ul class="aui-nav">
              <li><a href="http://example.com/">Server settings</a></li>
              <li><a href="http://example.com/">Database</a></li>
              <li><a href="http://example.com/">Application links</a></li>
              <li><a href="http://example.com/">Mail server</a></li>
              <li><a href="http://example.com/">Licensing</a></li>
            </ul>
            <div class="aui-nav-heading">
              <strong>Support</strong>
            </div>
            <ul class="aui-nav">
              <li><a href="http://example.com/">Atlassian Support
                  Tools</a></li>
              <li><a href="http://example.com/">Logging and Profiling</a></li>
            </ul>
          </div>
        </nav>
        <!-- Vertical Nav is usually placed inside aui-page-panel-nav -->
      </div>
      <section class="aui-page-panel-content">
        <h1>DataGrid </h1>
        <div style="width: 80%;" class="embedded-panel">
          <div class="dateGrid_operate">
            <table style="width: 100%;" id="dateGrid_operate" >
              <tbody>
                <tr>
                  <td style="width: 61%;">
                    <a class="aui-button" href="javascript:void(0)">
                       <span class="aui-icon aui-icon-small aui-iconfont-add" id="add-button"></span>add
                    </a> 
                    <a class="aui-button" href="javascript:void(0)" id="edit-button">
                      <span class="aui-icon aui-icon-small aui-iconfont-add"></span>edit
                    </a>
                     <a  class="aui-button" href="javascript:void(0)" id="delete-button">
                        <span class="aui-icon aui-icon-small aui-iconfont-close-dialog"></span>delete
                     </a>
                  </td>
                  <td style="white-space: nowrap;">
                  <form action="" class="aui">
                    <fieldset>
                      <input type="text"  class="text"  placeholder="please input  name" id="fname" name="fname">
                      <a class="aui-button"  href="javascript:void(0)">
                        <span class="aui-icon aui-icon-small aui-iconfont-search"></span>search
                      </a>
                    </fieldset>
                  </form>
                  </td>
                </tr>
              </tbody>
            </table><!--#dateGrid_operate  -->
          </div><!--.dateGrid_operate  -->
          <div class="dateGrid_all">
            <div class="title">
              <table class="aui "  id="dateGrid_tilte">
                <tr>
                 <th><input type="checkbox" id="checkboxAll"></th>
                 <th><div class="dateGrid_div">id</div></th>
                 <th><div class="dateGrid_div" >uploader</div></th>
                 <th><div class="dateGrid_div">name</div></th>
                 <th><div class="dateGrid_div">description</div></th>
                 <th><div class="dateGrid_div">project</div></th>
                 <th><div class="dateGrid_div">version</div></th>
                 <th><div class="dateGrid_div">category</div></th>
                 <th><div class="dateGrid_div">tags</div></th>
                 <th><div class="dateGrid_div">downloads</div></th>
                 <th style="width: 2%"></th>
               </tr>
              </table>
            </div><!--.title  -->
            <div class="content">
              <div>
                <table class="aui"  id="dateGrid">
                 <c:forEach items="${jarFiles.content }" var="jarFile">
                  <tr>
                    <td id="checkbox"><input type="checkbox"  name="checkbox" value="${jarFile.id }"></td>
                    <td id="id"><div class="dateGrid_div">${jarFile.id }</div></td>
                    <td id="uploader"><div class="dateGrid_div">${jarFile.uploader }</div></td>
                    <td id="name"><div class="dateGrid_div">${jarFile.name }</div></td>
                    <td id="description"><div class="dateGrid_div">${jarFile.description }</div></td>
                    <td id="project"><div class="dateGrid_div">${jarFile.project }</div></td>
                    <td id="version"><div class="dateGrid_div">${jarFile.version }</div></td>
                    <td id="category"><div class="dateGrid_div">${jarFile.category }</div></td>
                    <td id="tags"><div class="dateGrid_div">${jarFile.tags }</div></td>
                    <td id="downloads"><div class="dateGrid_div">${jarFile.downloads }</div></td>
                  </tr>
                </c:forEach>
                </table>
                <script type="text/javascript">
                <!--
                 //senfe("表格名称","奇数行背景","偶数行背景","鼠标经过背景","点击后背景");
                 senfe("dateGrid","#fff","#fff","#f5f5f5","#fff");
                -->
                $(function(){
                  $("#checkboxAll").click(function(){
                      //判断checkbox是否被选中
                      var bischecked=$("#checkboxAll").is(":checked");
                      var checkbox=$("[name=checkbox]:checkbox");
                      bischecked?checkbox.attr("checked",true):checkbox.attr("checked",false);
                  });
                 //删除表格中的数据操作
                 var checkbox_del_ids = new Array();
                 $("#delete-button").click(function(){
                   $('input[name="checkbox"]:checked').each(function(){
                     checkbox_del_ids.push($(this).val());
                   });
                   if(checkbox_del_ids.length == 0){
                       alert("Please select item(s) to delete.");
                   }else{
                     $.ajax({
                         type : "post",
                         data : "checkbox_del_ids=" + checkbox_del_ids.join(','),
                         url :  "rest/deleteDataById", 
                         dataType : "text",
                         success : function(request){
                           //刷新重新加载
                            document.location.reload();
                         },
                         error :  function(request,error){ 
                             alert('Error deleting item(s), try again later.'); 
                         }
                     });//$ajax
                   }//end if else
                 });//#delete-button
                 //更新表格数据的操作
                 var checkbox_edit_id = "";
                 AJS.$("#edit-button").click(function(){
                   $('input[name="checkbox"]:checked').each(function(){
                     checkbox_edit_id = $(this).val();
                     return false;
                   });
                   if(checkbox_edit_id == ""){
                       alert("Please select item to edit.");
                   }else{
                      $.ajax({
                         type : "POST",
                         data : JSON.stringify({id : checkbox_edit_id}),
                         url :  "rest/getDataById", 
                         dataType : "json",
                         contentType: "application/json;charset=utf-8",
                         success : function(data){
                        	 //设置对应的键值对
                           $("#edit-dialog #id").attr("value",data.id);
                           $("#edit-dialog #download").attr("value",data.download);
                           $("#edit-dialog #name").attr("value",data.name);
                           $("#edit-dialog #category").attr("value",data.category);
                           dialog.show();
                         },
                         error :  function(request,error){ 
                             alert('Error editing item, try again later.'); 
                         },
                         statusCode: {
                              404: function() {
                                  alert("page not found");
                                }
                           }
                     });//$ajax
                    // 打开eidt对话框
                   }//end if else
                 });//#edit-button
                });//function 全局的
                
                //edit对话框
                var dialog = new AJS.Dialog({
                    width: 400, 
                    height: 360, 
                    id: "edit-dialog", 
                    closeOnOutsideClick: true
                });
                dialog.addHeader("Edit");
                dialog.addPanel("EditPanel", $('#edit-panel').html(), "panel-body");
                dialog.addButton("OK", function (dialog) {
                  //获取edit对话框数据
                var t_id=$("#edit-dialog #id").attr("value");
                var t_download=$("#edit-dialog #download").attr("value");
                var t_name=$("#edit-dialog #name").attr("value");
                var t_category=$("#edit-dialog #category").attr("value");
                var content=JSON.stringify({id : t_id, download : t_download, name : t_name, category : t_category});
                alert(content);
                $.ajax({
                  type:"post",//使用post方法访问后台
                  async: false,
                  dateType:"json",//返回json格式的数据
                  url:"rest/updateData",//要访问的后台地址
                  contentType: "application/json;charset=utf-8",
                  data:JSON.stringify({id : t_id, download : t_download, name : t_name, category : t_category}),//要发送的数据
                  beforeSend:function(){
                    $("#edit-dialog #tip").html("<font color='red'>数据处理中,请稍后...</font>");
                  },
                  complete :function(){
                    $("#edit-dialog #tip").html("<font color='red'>数据处理完毕</font>");
                 },//AJAX请求完成时
                 success:function(data){//data为返回的数据，在这里做数据绑定
                   alert(data.name);
                 },
                 error: function(data,status,er) { 
                   alert("error: "+data+" status: "+status+" er:"+er);
                 },
                 statusCode: {
                     400: function() {
                         alert("输入的格式不对啊.");
                       }
                  }
                });
              });
              dialog.addLink("Cancel", function (dialog) {
                  dialog.hide();
                  document.location.reload();
              }, "#");
            //edit对话框--结束
              </script>
              </div>
            </div><!--.content  -->
          </div><!--.dateGrid_all  -->
          <div class="dateGrid-page" style="display: block;">
            <div class="page">
              <div class="page-left">
                <table cellspacing="0" cellpadding="0" border="0">
                  <tbody>
                    <tr>
                      <td id="control">
                        <span class="page-size">
                          <select class="select text">
                            <option>10</option>
                            <option>20</option>
                            <option>50</option>
                            <option>100</option>
                          </select>
                          <span class="separator"></span>
                        </span>
                        <a href="javascript:void(0)" style="text-decoration: none;" class="page-a"><span ><b>|&lt;</b></span>&nbsp;</a>
                        <a href="javascript:void(0)" style="text-decoration: none;" class="page-a"><span ><b>&lt;</b></span>&nbsp;</a>
                        <span>
                          <input type="text" class="page-num text">
                          <span>/sum</span>
                        </span>
                        <a href="javascript:void(0)" style="text-decoration: none;" class="page-a">&nbsp;<span ><b>&gt;</b></span></a>
                        <a href="javascript:void(0)" style="text-decoration: none;" class="page-a">&nbsp;<span ><b>&gt;|</b></span></a>
                        <span class="separator"></span>
                        <a href="javascript:document.location.reload();" class="page-a"><span class="aui-icon aui-icon-small aui-iconfont-build"></span></a>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div><!--.page-left  -->
              <div class="page-right">
                每页 10 条,共 10 条
              </div><!--.page-right  -->
            </div><!--.page  -->
           </div><!-- .dateGrid_page -->
        </div><!--.embedded-panel  -->
      </section>
    </div>
    <!--.aui-page-panel-inner  -->
  </div>
  <!--.aui-page-panel  -->
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />