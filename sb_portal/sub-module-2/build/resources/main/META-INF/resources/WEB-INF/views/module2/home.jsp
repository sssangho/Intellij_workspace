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
            background-image: url('https://images.unsplash.com/photo-1579547621113-e4bb2a19bdd6?q=80&w=2070&auto=format&fit=crop');
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
            background-color: #3498db;
            color: white;
            padding: 20px;
            text-align: center;
            position: relative;
            overflow: hidden;
            border-radius: 0 0 30px 30px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            background-image: url('https://images.unsplash.com/photo-1550751827-4bd374c3f58b?q=80&w=2070&auto=format&fit=crop');
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
            background: linear-gradient(135deg, rgba(52, 152, 219, 0.8), rgba(41, 128, 185, 0.9));
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
            background: linear-gradient(to right, #3498db, #2980b9);
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
            background-color: #3498db;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 20px;
            flex-shrink: 0;
            box-shadow: 0 5px 15px rgba(52, 152, 219, 0.3);
        }
        
        .feature-icon i {
            font-size: 2rem;
            color: white;
        }
        
        .feature-content {
            flex-grow: 1;
        }
        
        .feature h3 {
            color: #3498db;
            margin-top: 0;
            font-size: 1.4rem;
        }
        
        .feature p {
            margin-bottom: 15px;
            color: #666;
        }
        
        .feature a {
            display: inline-block;
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 30px;
            font-weight: 500;
            transition: all 0.3s ease;
            box-shadow: 0 3px 10px rgba(52, 152, 219, 0.3);
        }
        
        .feature a:hover {
            background-color: #2980b9;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(52, 152, 219, 0.4);
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
        <p>${portalName}의 두 번째 모듈입니다.</p>
    </header>
    
    <div class="container">
        <jsp:include page="/WEB-INF/views/common/navigation.jsp" />
        
        <div class="content">
            <h2>모듈 2 기능</h2>
            
            <div class="feature">
                <div class="feature-icon">
                    <i class="fas fa-database"></i>
                </div>
                <div class="feature-content">
                    <h3>기능 1</h3>
                    <p>모듈 2의 첫 번째 기능입니다. 이 기능은 데이터 관리 및 분석을 위한 고급 도구를 제공합니다. 자세한 내용을 확인하려면 아래 버튼을 클릭하세요.</p>
                    <a href="<c:url value='/module2/feature1'/>">자세히 보기</a>
                </div>
            </div>
            
            <div class="feature">
                <div class="feature-icon">
                    <i class="fas fa-chart-pie"></i>
                </div>
                <div class="feature-content">
                    <h3>기능 2</h3>
                    <p>모듈 2의 두 번째 기능입니다. 데이터 시각화 및 리포팅 기능을 통해 중요한 비즈니스 인사이트를 제공합니다. 현재 개발 중인 기능입니다.</p>
                    <a href="#" style="background-color: #999;">준비 중</a>
                </div>
            </div>
            
            <div class="feature">
                <div class="feature-icon">
                    <i class="fas fa-cloud"></i>
                </div>
                <div class="feature-content">
                    <h3>기능 3</h3>
                    <p>모듈 2의 세 번째 기능입니다. 클라우드 기반 데이터 저장 및 동기화 기능을 제공하여 언제 어디서나 데이터에 접근할 수 있습니다. 현재 개발 중인 기능입니다.</p>
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