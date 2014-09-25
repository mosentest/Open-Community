<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="edit-panel" style="display:none;">
  <form action=""  class="aui top-label"  id="editform">
    <fieldset>
       <input class="text" type="hidden" id="id" name="id">
      <div class="field-group">
        <label for="download">Download</label>
        <input class="text" type="text" id="download" name="download">
      </div>
      <div class="field-group">
        <label for="name">Name</label>
        <input class="text" type="text" id="name" name="name">
      </div>
      <div class="field-group">
        <label for="category">Category</label>
        <input class="text" type="text" id="category" name="category">
      </div>
      <div class="field-group"><span id="tip"></span></div>
    </fieldset>
  </form>
</div>