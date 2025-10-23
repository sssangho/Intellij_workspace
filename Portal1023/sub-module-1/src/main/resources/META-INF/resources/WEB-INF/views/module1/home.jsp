<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${moduleName} - ${portalName}</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            background-image: url('https://images.unsplash.com/photo-1557682250-33bd709cbe85?q=80&w=2029&auto=format&fit=crop');
            background-size: cover;
            background-attachment: fixed;
            background-position: center;
            position: relative;
        }
        
        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.9);
            z-index: -1;
        }
        
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            position: relative;
        }
        
        header {
            background-color: #e74c3c;
            color: white;
            padding: 20px;
            text-align: center;
            position: relative;
            overflow: hidden;
            border-radius: 0 0 30px 30px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            background-image: url('https://images.unsplash.com/photo-1551434678-e076c223a692?q=80&w=2070&auto=format&fit=crop');
            background-size: cover;
            background-position: center;
        }
        
        header::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(135deg, rgba(231, 76, 60, 0.8), rgba(192, 57, 43, 0.9));
            z-index: 0;
        }
        
        header h1, header p {
            position: relative;
            z-index: 1;
        }
        
        .content {
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            margin-top: 20px;
            padding: 30px;
            position: relative;
            overflow: hidden;
        }
        
        .content::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 5px;
            background: linear-gradient(to right, #e74c3c, #c0392b);
        }
        
        .feature {
            margin: 30px 0;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.05);
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
        }
        
        .feature:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }
        
        .feature-icon {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background-color: #e74c3c;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 20px;
            flex-shrink: 0;
            box-shadow: 0 5px 15px rgba(231, 76, 60, 0.3);
        }
        
        .feature-icon i {
            font-size: 2rem;
            color: white;
        }
        
        .feature-content {
            flex-grow: 1;
        }
        
        .feature h3 {
            color: #e74c3c;
            margin-top: 0;
            font-size: 1.4rem;
        }
        
        .feature p {
            margin-bottom: 15px;
            color: #666;
        }
        
        .feature a {
            display: inline-block;
            background-color: #e74c3c;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 30px;
            font-weight: 500;
            transition: all 0.3s ease;
            box-shadow: 0 3px 10px rgba(231, 76, 60, 0.3);
        }
        
        .feature a:hover {
            background-color: #c0392b;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(231, 76, 60, 0.4);
        }
        
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #333;
            text-decoration: none;
        }
        
        .back-link:hover {
            text-decoration: underline;
        }
        
        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 20px 0;
            margin-top: 40px;
            border-radius: 30px 30px 0 0;
            background-image: url('https://images.unsplash.com/photo-1483401757487-2ced3fa77952?q=80&w=2073&auto=format&fit=crop');
            background-size: cover;
            background-position: center;
            position: relative;
        }
        
        footer::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(51, 51, 51, 0.9);
            border-radius: 30px 30px 0 0;
            z-index: 0;
        }
        
        footer p {
            position: relative;
            z-index: 1;
        }
    </style>
</head>
<body>
    <header>
        <h1>${moduleName}</h1>
        <p>${portalName}의 첫 번째 모듈입니다.</p>
    </header>
    
    <div class="container">
        <jsp:include page="/WEB-INF/views/common/navigation.jsp" />
        
        <div class="content">
            <h2>모듈 1 기능</h2>
            
            <div class="feature">
                <div class="feature-icon">
                    <i class="fas fa-rocket"></i>
                </div>
                <div class="feature-content">
                    <h3>기능 1</h3>
                    <p>모듈 1의 첫 번째 기능입니다. 이 기능은 사용자에게 빠르고 효율적인 서비스를 제공합니다. 자세한 내용을 확인하려면 아래 버튼을 클릭하세요.</p>
                    <a href="<c:url value='/module1/feature1'/>">자세히 보기</a>
                </div>
            </div>
            
            <div class="feature">
                <div class="feature-icon">
                    <i class="fas fa-chart-line"></i>
                </div>
                <div class="feature-content">
                    <h3>기능 2</h3>
                    <p>모듈 1의 두 번째 기능입니다. 데이터 분석과 시각화를 통해 중요한 인사이트를 제공합니다. 현재 개발 중인 기능입니다.</p>
                    <a href="#" style="background-color: #999;">준비 중</a>
                </div>
            </div>
            
            <div class="feature">
                <div class="feature-icon">
                    <i class="fas fa-cog"></i>
                </div>
                <div class="feature-content">
                    <h3>기능 3</h3>
                    <p>모듈 1의 세 번째 기능입니다. 시스템 설정 및 관리를 위한 다양한 도구를 제공합니다. 현재 개발 중인 기능입니다.</p>
                    <a href="#" style="background-color: #999;">준비 중</a>
                </div>
            </div>
        </div>
    </div>
    
    <footer>
        <p>&copy; 2023 ${portalName}. All rights reserved.</p>
    </footer>
</body>
</html> 