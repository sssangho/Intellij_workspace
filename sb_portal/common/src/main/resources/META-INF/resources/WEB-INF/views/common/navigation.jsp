<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="nav-bar">
    <div class="nav-container">
        <c:forEach items="${navigationItems}" var="item">
            <a href="${item.url}" class="nav-item">
                <i class="fas ${item.icon}"></i> ${item.name}
            </a>
        </c:forEach>
    </div>
</div>

<style>
    /* 네비게이션 바 스타일 */
    .nav-bar {
        background-color: white;
        padding: 15px 0;
        border-radius: 50px;
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        margin-top: -25px;
        position: relative;
        z-index: 10;
        animation: fadeInUp 1s ease 0.6s forwards;
        opacity: 0;
    }
    
    .nav-container {
        display: flex;
        justify-content: center;
        gap: 20px;
    }
    
    .nav-item {
        display: flex;
        align-items: center;
        padding: 10px 20px;
        border-radius: 30px;
        text-decoration: none;
        color: #333333;
        font-weight: 500;
        transition: all 0.3s ease;
    }
    
    .nav-item:hover {
        background-color: rgba(67, 97, 238, 0.1);
        transform: translateY(-3px);
    }
    
    .nav-item i {
        margin-right: 8px;
        color: #4361ee;
    }
    
    @keyframes fadeInUp {
        from {
            opacity: 0;
            transform: translateY(30px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    
    @media (max-width: 768px) {
        .nav-container {
            flex-wrap: wrap;
            justify-content: center;
        }
    }
</style> 