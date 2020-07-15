<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%-- <%@ include file="../commons/include/bootstrap_header.jsp" %> --%>
<%@ include file="../commons/include/main_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
.table-expand {
  margin-top: 5rem;
}

.table-expand td {
  color: #8a8a8a;
}

.table-expand tr {
  border: 1px solid #e6e6e6;
}

.table-expand .text-center {
  padding-right: 3rem;
}

.table-expand-row.is-active .expand-icon::after {
  content: '-';
}

.table-expand-row .expand-icon::after {
  content: '+';
  float: right;
}

.table-expand-row-content {
  display: none;
}

.table-expand-row-content.is-active {
  display: table-row;
  -webkit-animation: fadeIn ease-in 1;
          animation: fadeIn ease-in 1;
  -webkit-animation-fill-mode: both;
          animation-fill-mode: both;
  -webkit-animation-duration: 0.5s;
          animation-duration: 0.5s;
}

.table-expand-row-nested {
  background-color: #e6e6e6;
}

@-webkit-keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}


.searchbar {
  margin: 1rem;
}

.search-field {
  width: 0;
  height: 40px;
  margin-left: 1rem;
  padding: 0;
  border-radius: 50px;
  border: none;
  transition: all 0.5s ease;
}

.expand-search {
  width: 80%;
  max-width: calc(80% - 3rem);
  border: 1px solid #c9c9c9;
  padding: .5rem;
}

svg {
  width: 20px;
  height: 20px;
}

.button {
  border-radius: 50px;
}


</style>
</head>
<body>
<div class="row">
	<!-- 검색바 -->
	
	<div class="input-group searchbar">
	  <div class="input-group-button">
	    <button class="button search"><svg width="100" height="100" viewBox="0 0 100 100">
	  <path fill="#FFF" fill-rule="evenodd" d="M42.117,12.246 C50.1209,12.246 57.797,15.4257 63.453,21.0858 C69.1132,26.742 72.2928,34.4178 72.2928,42.4218 C72.2928,50.4258 69.1131,58.1018 63.453,63.7578 C57.7968,69.418 50.121,72.5976 42.117,72.5976 C34.1131,72.5976 26.437,69.4179 20.781,63.7578 C15.1208,58.1016 11.9412,50.4258 11.9412,42.4218 C11.9412,34.4178 15.1209,26.7418 20.781,21.0858 C26.4372,15.4256 34.113,12.246 42.117,12.246 L42.117,12.246 Z M76.0828827,67.3362833 C82.3527829,58.7859894 85.2617455,48.0434678 83.9173,37.22271 C82.0618,22.28871 72.3743,9.47671 58.5153,3.61771 L58.51139,3.61771 C53.32389,1.41851 47.74139,0.28961 42.10539,0.29741 L42.117,0.305 C29.058,0.30891 16.742,6.3675 8.769001,16.707 C0.7924008,27.047 -1.933999,40.5 1.382301,53.129 C4.698701,65.758 13.6833,76.137 25.7103,81.223 L25.7103,81.22691 C39.5733,87.08631 55.5113,85.10191 67.5153,76.02771 C67.5852345,75.9748392 67.6549736,75.9217748 67.724517,75.8685177 L91.555,99.6990032 L100.0003,91.253703 L76.0828827,67.3362833 Z"/>
	</svg>
	</button>
	  </div>
	  <input class="input-field search-field" type="search" placeholder="Search for anything..." />
	</div>
	

	<!-- 게시판 시작 -->
	  <div class="columns small-10 small-centered">
	  	<table class="table-expand">
	  <thead>
	    <tr class="table-expand-row">
	      <th class="text-center" width="200">Date</th>
	      <th class="text-center">Number of items</th>
	      <th class="text-center" width="150">Amount</th>
	      <th class="text-center" width="150">Status</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr class="table-expand-row" data-open-details>
	      <td>August 15</td>
	      <td>2 items</td>
	      <td class="text-right">$0.50</td>
	      <td>in progress <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>July 15</td>
	      <td>4 items</td>
	      <td class="text-right">$1.30</td>
	      <td>scheduled <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>June 15</td>
	      <td>1 item</td>
	      <td class="text-right">$0.10</td>
	      <td>carried over <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>May 15</td>
	      <td>1 item</td>
	      <td class="text-right">$0.10</td>
	      <td>carried over <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>April 15</td>
	      <td>1 item</td>
	      <td class="text-right">$0.10</td>
	      <td>carried over <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>March 15</td>
	      <td>1 item</td>
	      <td class="text-right">$0.10</td>
	      <td>carried over <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>February 15</td>
	      <td>2 items</td>
	      <td class="text-right">$1.20</td>
	      <td>paid <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>January 15</td>
	      <td>5 items</td>
	      <td class="text-right">$0.50</td>
	      <td>carried over <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
	    <tr class="table-expand-row" data-open-details>
	      <td>December 15</td>
	      <td>2 items</td>
	      <td class="text-right">$0.20</td>
	      <td>carried over <span class="expand-icon"></span></td>
	    </tr>
	
	    <tr class="table-expand-row-content">
	      <td colspan="8" class="table-expand-row-nested">
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque unde quaerat reprehenderit ipsa ipsam, adipisci facere repellendus impedit at, quisquam dicta optio veniam quia nesciunt, inventore quod in neque magni?</p>
	      </td>
	      </td>
	    </tr>
	
		</tbody>
		</table>
	  </div>
	  <!-- 게시판 끝 -->
</div>
</body>
</html>
<%-- <%@ include file="../commons/include/bootstrap_footer.jsp" %> --%>
<%@ include file="list_include_js.jsp" %>