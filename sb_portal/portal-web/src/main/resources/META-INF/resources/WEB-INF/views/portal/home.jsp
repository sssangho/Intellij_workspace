<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${portalName}</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4361ee;
            --secondary-color: #3a0ca3;
            --accent-color: #f72585;
            --background-color: #f8f9fa;
            --card-color: #ffffff;
            --text-color: #333333;
            --text-light: #666666;
            --shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            --transition: all 0.3s ease;
        }
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
            line-height: 1.6;
            background-image: url('https://images.unsplash.com/photo-1557683316-973673baf926?q=80&w=2029&auto=format&fit=crop');
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
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            position: relative;
        }
        
        /* 헤더 스타일 */
        .hero {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            padding: 40px 0 50px;
            text-align: center;
            position: relative;
            overflow: hidden;
            border-radius: 0 0 30px 30px;
            box-shadow: var(--shadow);
            background-image: url('https://images.unsplash.com/photo-1451187580459-43490279c0fa?q=80&w=2072&auto=format&fit=crop');
            background-size: cover;
            background-position: center;
        }
        
        .hero::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(135deg, rgba(67, 97, 238, 0.8), rgba(58, 12, 163, 0.9));
        }
        
        .hero-content {
            position: relative;
            z-index: 1;
        }
        
        .hero h1 {
            font-size: 2.5rem;
            margin-bottom: 10px;
            font-weight: 700;
            text-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
            animation: fadeInDown 1s ease;
        }
        
        .hero p {
            font-size: 1.1rem;
            opacity: 0.9;
            animation: fadeInUp 1s ease 0.3s forwards;
            opacity: 0;
        }
        
        /* 네비게이션 바 스타일 */
        .nav-bar {
            background-color: white;
            padding: 15px 0;
            border-radius: 50px;
            box-shadow: var(--shadow);
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
            color: var(--text-color);
            font-weight: 500;
            transition: var(--transition);
        }
        
        .nav-item:hover {
            background-color: rgba(67, 97, 238, 0.1);
            transform: translateY(-3px);
        }
        
        .nav-item i {
            margin-right: 8px;
            color: var(--primary-color);
        }
        
        /* 모듈 컨테이너 스타일 */
        .modules-section {
            margin: 30px 0;
        }
        
        .modules-container {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
        }
        
        .module-wrapper {
            flex: 1;
            min-width: 300px;
            background-color: white;
            border-radius: 15px;
            box-shadow: var(--shadow);
            overflow: hidden;
            transition: var(--transition);
        }
        
        .module-wrapper:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
        }
        
        .module-header {
            position: relative;
            height: 150px;
            overflow: hidden;
        }
        
        .module-header-bg {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-size: cover;
            background-position: center;
            transition: var(--transition);
        }
        
        .module-wrapper:hover .module-header-bg {
            transform: scale(1.1);
        }
        
        .module-header-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(to bottom, rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.7));
            display: flex;
            align-items: center;
            padding: 20px;
        }
        
        .module-info {
            display: flex;
            align-items: center;
            z-index: 1;
        }
        
        .module-icon {
            width: 40px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.2);
            border-radius: 50%;
            margin-right: 15px;
            font-size: 1.2rem;
            color: white;
        }
        
        .module-title {
            font-size: 1.5rem;
            font-weight: 600;
            color: white;
            margin: 0;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
        }
        
        .module-content {
            padding: 0;
            height: 350px;
            overflow: hidden;
            position: relative;
        }
        
        .module-frame {
            width: 100%;
            height: 100%;
            border: none;
            overflow: hidden;
        }
        
        .loading {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
            z-index: 5;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
        }
        
        .loading i {
            font-size: 3rem;
            color: var(--primary-color);
            animation: spin 1s linear infinite;
        }
        
        .module-footer {
            padding: 15px;
            background-color: #f8f9fa;
            border-top: 1px solid #eee;
            text-align: center;
        }
        
        .module-link {
            display: inline-block;
            padding: 8px 20px;
            background-color: var(--primary-color);
            color: white;
            text-decoration: none;
            border-radius: 30px;
            font-weight: 500;
            transition: var(--transition);
        }
        
        .module-link:hover {
            background-color: var(--secondary-color);
            transform: translateY(-2px);
        }
        
        /* 푸터 스타일 */
        footer {
            background-color: #2b2d42;
            color: white;
            text-align: center;
            padding: 30px 0;
            margin-top: 50px;
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
            background-color: rgba(43, 45, 66, 0.9);
            border-radius: 30px 30px 0 0;
        }
        
        .footer-content {
            display: flex;
            flex-direction: column;
            align-items: center;
            position: relative;
            z-index: 1;
        }
        
        .footer-logo {
            font-size: 1.5rem;
            font-weight: 700;
            margin-bottom: 15px;
            color: white;
        }
        
        .footer-links {
            display: flex;
            gap: 20px;
            margin-bottom: 15px;
        }
        
        .footer-links a {
            color: rgba(255, 255, 255, 0.8);
            text-decoration: none;
            transition: var(--transition);
        }
        
        .footer-links a:hover {
            color: white;
        }
        
        .social-icons {
            display: flex;
            gap: 15px;
            margin-bottom: 15px;
        }
        
        .social-icons a {
            display: inline-block;
            width: 35px;
            height: 35px;
            line-height: 35px;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
            color: white;
            transition: var(--transition);
        }
        
        .social-icons a:hover {
            background-color: var(--accent-color);
            transform: translateY(-3px);
        }
        
        .copyright {
            font-size: 0.9rem;
            color: rgba(255, 255, 255, 0.6);
        }
        
        /* 애니메이션 */
        @keyframes fadeInDown {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
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
        
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
        
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
        
        /* 반응형 디자인 */
        @media (max-width: 768px) {
            .hero h1 {
                font-size: 2rem;
            }
            
            .hero p {
                font-size: 0.9rem;
            }
            
            .modules-container {
                flex-direction: column;
            }
            
            .module-content {
                height: 300px;
            }
            
            .nav-container {
                flex-wrap: wrap;
                justify-content: center;
            }
        }
    </style>
</head>
<body>
    <section class="hero">
        <div class="hero-content">
            <h1>${portalName}</h1>
            <p>버전: ${version} - 통합 포털 시스템</p>
        </div>
    </section>
    
    <div class="container">
        <div class="nav-bar">
            <div class="nav-container">
                <c:forEach items="${navigationItems}" var="item">
                    <a href="${item.url}" class="nav-item">
                        <i class="fas ${item.icon}"></i> ${item.name}
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    
    <div class="container modules-section">
        <div class="modules-container">
            <div class="module-wrapper">
                <div class="module-header">
                    <div class="module-header-bg" style="background-image: url('https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?q=80&w=2070&auto=format&fit=crop');"></div>
                    <div class="module-header-overlay">
                        <div class="module-info">
                            <div class="module-icon">
                                <i class="fas fa-cube"></i>
                            </div>
                            <h2 class="module-title">모듈 1</h2>
                        </div>
                    </div>
                </div>
                <div class="module-content">
                    <div class="loading" id="loading1">
                        <i class="fas fa-spinner"></i>
                        <p>로딩 중...</p>
                    </div>
                    <iframe src="http://localhost:8081/module1/" class="module-frame" id="module1Frame" onload="hideLoading('loading1')" scrolling="no"></iframe>
                </div>
                <div class="module-footer">
                    <a href="http://localhost:8081/module1/" target="_blank" class="module-link">모듈 1 전체보기</a>
                </div>
            </div>
            
            <div class="module-wrapper">
                <div class="module-header">
                    <div class="module-header-bg" style="background-image: url('https://images.unsplash.com/photo-1581092918056-0c4c3acd3789?q=80&w=2070&auto=format&fit=crop');"></div>
                    <div class="module-header-overlay">
                        <div class="module-info">
                            <div class="module-icon">
                                <i class="fas fa-cogs"></i>
                            </div>
                            <h2 class="module-title">모듈 2</h2>
                        </div>
                    </div>
                </div>
                <div class="module-content">
                    <div class="loading" id="loading2">
                        <i class="fas fa-spinner"></i>
                        <p>로딩 중...</p>
                    </div>
                    <iframe src="http://localhost:8082/module2/" class="module-frame" id="module2Frame" onload="hideLoading('loading2')" scrolling="no"></iframe>
                </div>
                <div class="module-footer">
                    <a href="http://localhost:8082/module2/" target="_blank" class="module-link">모듈 2 전체보기</a>
                </div>
            </div>
        </div>
    </div>
    
    <footer>
        <div class="container">
            <div class="footer-content">
                <div class="footer-logo">${portalName}</div>
                <div class="footer-links">
                    <c:forEach items="${navigationItems}" var="item">
                        <a href="${item.url}" target="_blank">${item.name}</a>
                    </c:forEach>
                </div>
                <div class="social-icons">
                    <a href="#"><i class="fab fa-github"></i></a>
                    <a href="#"><i class="fab fa-twitter"></i></a>
                    <a href="#"><i class="fab fa-linkedin"></i></a>
                </div>
                <div class="copyright">
                    <p>&copy; 2023 ${portalName}. All rights reserved.</p>
                </div>
            </div>
        </div>
    </footer>
    
    <script>
        function hideLoading(loadingId) {
            document.getElementById(loadingId).style.display = 'none';
        }
    </script>
</body>
</html> 