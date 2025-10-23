<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${moduleName} - ${portalName}</title>
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
            height: 100vh;
            display: flex;
            flex-direction: column;
        }
        
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        /* 헤더 스타일 */
        .header {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            padding: 20px 0;
            text-align: center;
            position: relative;
            box-shadow: var(--shadow);
        }
        
        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .logo {
            font-size: 1.5rem;
            font-weight: 700;
            text-decoration: none;
            color: white;
            display: flex;
            align-items: center;
        }
        
        .logo i {
            margin-right: 10px;
        }
        
        .nav-links {
            display: flex;
            gap: 20px;
        }
        
        .nav-links a {
            color: white;
            text-decoration: none;
            padding: 8px 15px;
            border-radius: 50px;
            transition: var(--transition);
        }
        
        .nav-links a:hover {
            background-color: rgba(255, 255, 255, 0.1);
        }
        
        .nav-links a.active {
            background-color: var(--accent-color);
            font-weight: 500;
        }
        
        .module-title {
            text-align: center;
            margin: 20px 0;
            font-size: 1.8rem;
            color: var(--primary-color);
        }
        
        /* iframe 컨테이너 */
        .iframe-container {
            flex: 1;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: var(--shadow);
            margin-bottom: 20px;
            position: relative;
            overflow: hidden;
            height: calc(100vh - 180px);
        }
        
        .module-iframe {
            width: 100%;
            height: 100%;
            border: none;
            border-radius: 5px;
        }
        
        /* 푸터 스타일 */
        footer {
            background-color: #2b2d42;
            color: white;
            text-align: center;
            padding: 20px 0;
            margin-top: auto;
        }
        
        .footer-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .footer-logo {
            font-size: 1.2rem;
            font-weight: 700;
            color: white;
        }
        
        .footer-links {
            display: flex;
            gap: 20px;
        }
        
        .footer-links a {
            color: rgba(255, 255, 255, 0.8);
            text-decoration: none;
            transition: var(--transition);
        }
        
        .footer-links a:hover {
            color: white;
        }
        
        .copyright {
            font-size: 0.9rem;
            color: rgba(255, 255, 255, 0.6);
        }
        
        /* 로딩 인디케이터 */
        .loading {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
        }
        
        .loading i {
            font-size: 3rem;
            color: var(--primary-color);
            animation: spin 1s linear infinite;
        }
        
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
        
        /* 반응형 디자인 */
        @media (max-width: 768px) {
            .header-content {
                flex-direction: column;
                gap: 15px;
            }
            
            .nav-links {
                flex-wrap: wrap;
                justify-content: center;
            }
            
            .iframe-container {
                height: calc(100vh - 250px);
            }
            
            .footer-content {
                flex-direction: column;
                gap: 15px;
            }
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="container">
            <div class="header-content">
                <a href="/" class="logo">
                    <i class="fas fa-home"></i> ${portalName}
                </a>
                <div class="nav-links">
                    <a href="/" class="nav-link">홈</a>
                    <a href="/module1" class="nav-link ${moduleName == '모듈 1' ? 'active' : ''}">모듈 1</a>
                    <a href="/module2" class="nav-link ${moduleName == '모듈 2' ? 'active' : ''}">모듈 2</a>
                </div>
            </div>
        </div>
    </header>
    
    <div class="container">
        <h2 class="module-title">${moduleName}</h2>
        
        <div class="iframe-container">
            <div class="loading" id="loading">
                <i class="fas fa-spinner"></i>
                <p>로딩 중...</p>
            </div>
            <iframe src="${moduleUrl}" class="module-iframe" id="moduleFrame" onload="hideLoading()"></iframe>
        </div>
    </div>
    
    <footer>
        <div class="container">
            <div class="footer-content">
                <div class="footer-logo">${portalName}</div>
                <div class="footer-links">
                    <a href="/">홈</a>
                    <a href="/module1">모듈 1</a>
                    <a href="/module2">모듈 2</a>
                </div>
                <div class="copyright">
                    <p>&copy; 2023 ${portalName}. All rights reserved.</p>
                </div>
            </div>
        </div>
    </footer>
    
    <script>
        function hideLoading() {
            document.getElementById('loading').style.display = 'none';
        }
        
        // iframe 내부 링크 클릭 시 로딩 표시
        document.getElementById('moduleFrame').addEventListener('load', function() {
            hideLoading();
        });
        
        // iframe 내부 링크 클릭 시 로딩 표시를 위한 이벤트 리스너
        document.getElementById('moduleFrame').onunload = function() {
            document.getElementById('loading').style.display = 'block';
        };
    </script>
</body>
</html> 