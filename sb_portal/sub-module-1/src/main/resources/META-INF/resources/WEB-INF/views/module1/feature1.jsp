<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${featureName} - ${moduleName}</title>
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
        
        .feature-header {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
        }
        
        .feature-icon {
            width: 60px;
            height: 60px;
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
            font-size: 1.8rem;
            color: white;
        }
        
        .feature-title {
            margin: 0;
            color: #e74c3c;
            font-size: 1.8rem;
        }
        
        .feature-image {
            width: 100%;
            height: 300px;
            border-radius: 10px;
            overflow: hidden;
            margin: 20px 0 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .feature-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: all 0.5s ease;
        }
        
        .feature-image:hover img {
            transform: scale(1.05);
        }
        
        .feature-description {
            margin-bottom: 30px;
            line-height: 1.8;
            color: #666;
        }
        
        .feature-details {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .detail-card {
            flex: 1;
            min-width: 250px;
            background-color: #f9f9f9;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.05);
            transition: all 0.3s ease;
        }
        
        .detail-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }
        
        .detail-card h4 {
            color: #e74c3c;
            margin-top: 0;
            display: flex;
            align-items: center;
        }
        
        .detail-card h4 i {
            margin-right: 10px;
        }
        
        .detail-card p {
            margin-bottom: 0;
            color: #666;
        }
        
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #333;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 30px;
            background-color: #f5f5f5;
            transition: all 0.3s ease;
            box-shadow: 0 3px 10px rgba(0,0,0,0.05);
        }
        
        .back-link:hover {
            background-color: #e0e0e0;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .back-link i {
            margin-right: 8px;
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
        <h1>${featureName}</h1>
        <p>${moduleName}의 기능 페이지입니다.</p>
    </header>
    
    <div class="container">
        <jsp:include page="/WEB-INF/views/common/navigation.jsp" />
        
        <div class="content">
            <div class="feature-header">
                <div class="feature-icon">
                    <i class="fas fa-rocket"></i>
                </div>
                <h2 class="feature-title">${featureName} 상세 정보</h2>
            </div>
            
            <div class="feature-image">
                <img src="https://images.unsplash.com/photo-1460925895917-afdab827c52f?q=80&w=2015&auto=format&fit=crop" alt="${featureName} 이미지">
            </div>
            
            <div class="feature-description">
                <p>이 페이지는 ${moduleName}의 ${featureName}에 대한 상세 정보를 제공합니다. 이 기능은 사용자에게 빠르고 효율적인 서비스를 제공하기 위해 설계되었습니다.</p>
                <p>실제 프로젝트에서는 이 부분에 기능에 관련된 자세한 내용이 표시됩니다. 아래에서 이 기능의 주요 특징을 확인할 수 있습니다.</p>
            </div>
            
            <div class="feature-details">
                <div class="detail-card">
                    <h4><i class="fas fa-bolt"></i> 빠른 처리 속도</h4>
                    <p>최적화된 알고리즘을 통해 데이터 처리 속도를 극대화하여 사용자에게 빠른 응답 시간을 제공합니다.</p>
                </div>
                
                <div class="detail-card">
                    <h4><i class="fas fa-shield-alt"></i> 보안 강화</h4>
                    <p>최신 보안 기술을 적용하여 사용자 데이터를 안전하게 보호하고 무단 접근을 방지합니다.</p>
                </div>
                
                <div class="detail-card">
                    <h4><i class="fas fa-sync-alt"></i> 실시간 업데이트</h4>
                    <p>데이터 변경 사항을 실시간으로 반영하여 항상 최신 정보를 확인할 수 있습니다.</p>
                </div>
            </div>
            
            <a href="<c:url value='/module1/' />" class="back-link"><i class="fas fa-arrow-left"></i> 모듈 1 홈으로 돌아가기</a>
        </div>
    </div>
    
    <footer>
        <p>&copy; 2023 ${portalName}. All rights reserved.</p>
    </footer>
</body>
</html> 