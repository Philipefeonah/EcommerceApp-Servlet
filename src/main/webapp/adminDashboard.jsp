<%@ page import="com.maxiflexy.model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/12/24
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard-Electron</title>
  <!-- Reuse existing CSS links from your initial snippet -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
  <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
  <link type="text/css" rel="stylesheet" href="css/slick.css"/>
  <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
  <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link type="text/css" rel="stylesheet" href="css/style.css"/>


  <style>
    /* Add some basic styling for modal */
    .modal {
      display: none;
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgb(0,0,0);
      background-color: rgba(0,0,0,0.4);
    }
    .modal-content {
      margin: 15% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
      background-color: white;
    }
    .close {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
    }
    .close:hover,
    .close:focus {
      color: black;
      text-decoration: none;
      cursor: pointer;
    }
  </style>


</head>
<body>
<div class="container mt-5">
  <h2 style="padding-top: 50px">ADMIN DASHBOARD</h2>
  <h2>Welcome </h2>

  <!-- Add Product Form -->
  <div class="section mt-5">
    <h3>Add New Product(s)</h3>
    <form action="addProduct" method="post" class="form-horizontal">
      <div class="form-group">
        <label for="name" class="control-label col-sm-2">Name:</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="name" name="name" placeholder="Enter product name" required>
        </div>
      </div>
      <div class="form-group">
        <label for="category" class="control-label col-sm-2">Category:</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="category" name="category" placeholder="Enter product category" required>
        </div>
      </div>
      <div class="form-group">
        <label for="price" class="control-label col-sm-2">Price:</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="price" name="price" placeholder="Enter product price" step="0.01" required>
        </div>
      </div>
      <div class="form-group">
        <label for="image" class="control-label col-sm-2">Image URL:</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="image" name="image" placeholder="Enter product image URL" required>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <a href="product_page.jsp"><button type="submit" class="btn btn-default">Add Product</button></a>
        </div>
      </div>
    </form>
  </div>

  <!-- List Products (This section should be dynamically populated) -->
  <div class="section">
    <h3>Manage Products</h3>
    <!-- Example table structure. Populate dynamically with product data -->
    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Category</th>
          <th>Price</th>
          <th>Image</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <%
          List<Product> productList = (List<Product>) request.getAttribute("products");
          if (productList != null) {
            for (Product product : productList) {
        %>
        <tr>
          <td><%= product.getId() %></td>
          <td><%= product.getName() %></td>
          <td><%= product.getCategory() %></td>
          <td>$<%= product.getPrice() %></td>
          <td>
            <img src="<%= request.getContextPath() %>/img/<%= product.getImage() %>" style="width: 50px; height: auto;" onclick="openModal(this)">
          </td>
          <td>
            <a href="deleteProduct?id=<%= product.getId() %>" class="btn btn-danger">Delete</a>
          </td>
<%--          <td><img src="<%= request.getContextPath() %>/img/<%= product.getImage() %>" style="width: 50px; height: auto;"></td>--%>
<%--          <td>--%>
<%--            <a href="deleteProduct?id=<%= product.getId() %>" class="btn btn-danger">Delete</a>--%>
<%--          </td>--%>
        </tr>
        <%
                }
            } else{
        %>
        <tr>
          <td colspan="6">No products available</td>
        </tr>
        <%
           }
        %>
      </tbody>
    </table>

    <!-- Modal for full-size image view -->
    <div id="imageModal" class="modal">
      <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <img id="modalImage" src="" style="width: 100%;">
      </div>
    </div>

    <script>
      function openModal(img) {
        var modal = document.getElementById("imageModal");
        var modalImage = document.getElementById("modalImage");
        modalImage.src = img.src;
        modal.style.display = "block";
      }

      function closeModal() {
        var modal = document.getElementById("imageModal");
        modal.style.display = "none";
      }
    </script>


  </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>