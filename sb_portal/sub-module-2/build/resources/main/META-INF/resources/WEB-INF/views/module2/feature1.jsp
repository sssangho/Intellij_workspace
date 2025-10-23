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
        
        .feature-header {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
        }
        
        .feature-icon {
            width: 60px;
            height: 60px;
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
            font-size: 1.8rem;
            color: white;
        }
        
        .feature-title {
            margin: 0;
            color: #3498db;
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
        
        .data-visualization {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .chart {
            flex: 1;
            min-width: 300px;
            height: 200px;
            background-color: #f9f9f9;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.05);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            transition: all 0.3s ease;
        }
        
        .chart:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }
        
        .chart-icon {
            font-size: 3rem;
            color: #3498db;
            margin-bottom: 15px;
        }
        
        .chart-title {
            font-size: 1.2rem;
            color: #333;
            margin-bottom: 10px;
            text-align: center;
        }
        
        .chart-description {
            font-size: 0.9rem;
            color: #666;
            text-align: center;
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
                    <i class="fas fa-database"></i>
                </div>
                <h2 class="feature-title">${featureName} 상세 정보</h2>
            </div>
            
            <div class="feature-image">
                <img src="https://images.unsplash.com/photo-1551288049-bebda4e38f71?q=80&w=2070&auto=format&fit=crop" alt="${featureName} 이미지">
            </div>
            
            <div class="feature-description">
                <p>이 페이지는 ${moduleName}의 ${featureName}에 대한 상세 정보를 제공합니다. 이 기능은 데이터 관리 및 분석을 위한 고급 도구를 제공합니다.</p>
                <p>실제 프로젝트에서는 이 부분에 기능에 관련된 자세한 내용이 표시됩니다. 아래에서 이 기능의 데이터 시각화 예시를 확인할 수 있습니다.</p>
            </div>
            
            <div class="data-visualization">
                <div class="chart">
                    <div class="chart-icon">
                        <i class="fas fa-chart-bar"></i>
                    </div>
                    <h3 class="chart-title">막대 그래프</h3>
                    <p class="chart-description">카테고리별 데이터 비교에 적합한 시각화 도구입니다.</p>
                </div>
                
                <div class="chart">
                    <div class="chart-icon">
                        <i class="fas fa-chart-line"></i>
                    </div>
                    <h3 class="chart-title">선 그래프</h3>
                    <p class="chart-description">시간에 따른 데이터 변화를 표시하는 데 적합합니다.</p>
                </div>
                
                <div class="chart">
                    <div class="chart-icon">
                        <i class="fas fa-chart-pie"></i>
                    </div>
                    <h3 class="chart-title">원형 그래프</h3>
                    <p class="chart-description">전체에 대한 부분의 비율을 시각적으로 표현합니다.</p>
                </div>
            </div>
            
            <a href="<c:url value='/module2/' />" class="back-link"><i class="fas fa-arrow-left"></i> 모듈 2 홈으로 돌아가기</a>
        </div>
    </div>
    
    <footer>
        <p>&copy; 2023 ${portalName}. All rights reserved.</p>
    </footer>
</body>
</html> 