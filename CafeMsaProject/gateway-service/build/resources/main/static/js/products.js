// ===== 전역 =====
let productModal;
let productDetailModal;      // ✅ 상세 모달
let currentRole = null;      // "ROLE_USER" / "ROLE_OWNER"
let currentUserId = null;    // 로그인 유저 ID (localStorage 에서 읽음)
let allProducts = [];        // 전체 상품 목록 캐시
let currentCategoryFilter = 'ALL'; // 'ALL' | '음료' | '푸드'

// ✅ 페이징 전역 변수 (1페이지부터 시작)
let currentPage = 1;         // 현재 페이지
const pageSize = 7;          // 한 페이지에 보여줄 상품 개수 (7개)

// 공통: role 정규화
function normalizeRole(role) {
    if (!role) return null;
    const r = String(role).toUpperCase();
    if (r.startsWith('ROLE_')) return r;
    if (r.includes('OWNER') || r.includes('ADMIN')) return 'ROLE_OWNER';
    if (r.includes('USER')) return 'ROLE_USER';
    return r;
}

// ===== 초기 진입 =====
document.addEventListener('DOMContentLoaded', async function () {
    productModal = new bootstrap.Modal(document.getElementById('productModal'));
    productDetailModal = new bootstrap.Modal(document.getElementById('productDetailModal'));

    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');
    const rawRole = localStorage.getItem('role');
    const rawUserId = localStorage.getItem('userId');

    if (!token || !username) {
        alert('로그인이 필요합니다.');
        location.href = '/login';
        return;
    }

    currentRole = normalizeRole(rawRole) || 'ROLE_USER';
    currentUserId = rawUserId ? Number(rawUserId) : 1;

    renderNavBar();          // nav-menu 없으면 그냥 무시됨
    setupUIByRole();
    updateProductPageTitle();   // ✅ 역할에 따라 제목 텍스트만 변경
    updateFilterButtons();
    await loadProducts();
});

// ===== 네비바 관련 =====
function renderNavBar() {
    const navMenu = document.getElementById('nav-menu');
    if (!navMenu) return;

    const activeClass = 'btn btn-nav-active';
    const normalClass = 'btn btn-nav-custom';
    const getClass = (path) => location.pathname.startsWith(path) ? activeClass : normalClass;

    if (currentRole === 'ROLE_OWNER') {
        navMenu.innerHTML = `
            <a href="/products" class="${getClass('/products')} me-2">상품목록</a>
            <a href="/order_products" class="${getClass('/orders/purchase')} me-2">발주</a>
            <a href="/order_orderlist" class="${getClass('/orders/purchase/history')} me-2">발주내역</a>
            <a href="/admin/users" class="${getClass('/admin/users')}">사용자 관리</a>
        `;
    } else if (currentRole === 'ROLE_USER') {
        navMenu.innerHTML = `
            <a href="/products" class="${getClass('/products')} me-2">상품목록</a>
            <a href="/cart" class="${getClass('/cart')} me-2">장바구니</a>
            <a href="/orders" class="${getClass('/orders')} me-2">상품내역</a>
            <a href="/favorites" class="${getClass('/favorites')}">즐겨찾기</a>
        `;
    } else {
        navMenu.innerHTML = `
            <a href="/products" class="${getClass('/products')} me-2">상품목록</a>
        `;
    }
}

function setupUIByRole() {
    const addBtn = document.getElementById('btn-add-product');
    const actionHeader = document.getElementById('th-action');

    if (currentRole === 'ROLE_OWNER') {
        if (addBtn) addBtn.style.display = 'inline-block';
        if (actionHeader) actionHeader.textContent = '작업';
    } else if (currentRole === 'ROLE_USER') {
        if (addBtn) addBtn.style.display = 'none';
        if (actionHeader) actionHeader.textContent = '장바구니';
    } else {
        if (addBtn) addBtn.style.display = 'none';
        if (actionHeader) actionHeader.textContent = '';
    }
}

// ✅ 역할에 따라 페이지 상단 제목 텍스트만 변경 (아이콘은 그대로 유지)
function updateProductPageTitle() {
    const titleSpan = document.getElementById('productPageTitleText');
    if (!titleSpan) return;

    if (currentRole === 'ROLE_OWNER') {
        titleSpan.textContent = '상품 관리';
    } else if (currentRole === 'ROLE_USER') {
        titleSpan.textContent = '상품목록';
    } else {
        titleSpan.textContent = '상품목록';
    }
}

// ===== 상품 조회 =====
async function loadProducts() {
    try {
        const response = await fetch('/api/products');
        if (!response.ok) {
            const text = await response.text().catch(() => '');
            throw new Error(`상품 목록 조회 실패 (${response.status}) ${text}`);
        }

        const products = await response.json();
        allProducts = products || [];
        currentPage = 1;          // 처음 로딩 시 1페이지
        renderProducts();
    } catch (error) {
        console.error('상품 목록을 불러오는데 실패했습니다:', error);
        alert('상품 목록을 불러오는데 실패했습니다.');
    }
}

// ===== 상품 렌더링 =====
function renderProducts() {
    const tbody = document.getElementById('productTableBody');
    if (!tbody) return;
    tbody.innerHTML = '';

    const fmt = (n) => {
        const v = Number(n || 0);
        try { return v.toLocaleString(); } catch { return String(v); }
    };

    const filtered = allProducts.filter(product => {
        if (currentCategoryFilter === 'ALL') return true;
        return product.category === currentCategoryFilter;
    });

    // ✅ 페이징 계산
    const totalItems = filtered.length;
    const totalPages = Math.ceil(totalItems / pageSize) || 1;

    if (currentPage > totalPages) currentPage = totalPages;
    if (currentPage < 1) currentPage = 1;

    const startIdx = (currentPage - 1) * pageSize;
    const endIdx = startIdx + pageSize;
    const pageItems = filtered.slice(startIdx, endIdx);

    // ✅ 페이징 버튼 렌더링 (버튼 스타일: 주문페이지와 동일)
    renderPagination(totalPages, currentPage);

    pageItems.forEach(product => {
        const tr = document.createElement('tr');

        const isSoldOut = !product.stock || product.stock <= 0;
        const soldOutText = isSoldOut ? '품절' : '판매중';

        let actionButtons = '';

        if (currentRole === 'ROLE_OWNER') {
            actionButtons = `
                <button class="btn btn-sm btn-primary me-1" onclick="editProduct(${product.id})">수정</button>
                <button class="btn btn-sm btn-danger" onclick="deleteProduct(${product.id})">삭제</button>
            `;
        }
        else if (currentRole === 'ROLE_USER') {
            if (!isSoldOut) {
                const pname  = String(product.name ?? '').replace(/'/g, "\\'");
                const pcat   = String(product.category ?? '').replace(/'/g, "\\'");
                const pprice = Number(product.price ?? 0);

                actionButtons = `
                    <button class="btn btn-sm btn-outline-coffee me-1"
                            onclick="addToCart(${product.id})">
                        <i class="bi bi-bag-check me-1"></i>담기
                    </button>
                    <button class="btn btn-sm btn-outline-danger"
                            style="border-color:#dc3545;color:#dc3545;"
                            onclick="addToFavorites(${product.id}, '${pname}', '${pcat}', ${pprice})">
                        ♡ 즐겨찾기
                    </button>
                `;
            } else {
                actionButtons = `<button class="btn btn-sm btn-secondary" disabled>품절</button>`;
            }
        }

        const imgSrc = product.imageUrl || product.image_url || '';
        const safeName = String(product.name ?? '').replace(/"/g, '&quot;');

        tr.innerHTML = `
            <td>${product.id}</td>
            <td>
                <div class="d-flex align-items-center" style="gap:10px;">
                    <img src="${imgSrc}"
                         alt="${safeName}"
                         class="rounded"
                         style="width:50px;height:50px;object-fit:cover;">
                    <button type="button"
                            class="btn btn-link p-0 text-decoration-none text-start"
                            onclick="showProductDetail(${product.id})">
                        ${product.name}
                    </button>
                </div>
            </td>
            <td>${fmt(product.price)}원</td>
            <td>${product.category || '-'}</td>
            <td>${soldOutText}</td>
            <td>${actionButtons}</td>
        `;
        tbody.appendChild(tr);
    });
}

// ✅ 페이지 이동
function goToPage(page) {
    currentPage = page;
    renderProducts();
}

// ✅ 페이징 버튼 렌더링 (주문 페이지 같은 네모 버튼 스타일)
function renderPagination(totalPages, page) {
    const paginationContainer = document.getElementById('productPagination');
    if (!paginationContainer) return;

    paginationContainer.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement('button');
        btn.type = 'button';
        btn.textContent = i;

        // 주문페이지와 동일하게: 현재 페이지는 진한 커피색, 나머지는 흰 배경 + 테두리
        btn.className = (i === page)
            ? 'btn btn-coffee me-2'
            : 'btn btn-outline-coffee me-2';

        btn.onclick = () => {
            if (i !== currentPage) {
                goToPage(i);
            }
        };
        paginationContainer.appendChild(btn);
    }
}

// ===== 카테고리 버튼 =====
function setCategoryFilter(filter) {
    currentCategoryFilter = filter;
    currentPage = 1;     // ✅ 카테고리 변경 시 1페이지로
    updateFilterButtons();
    renderProducts();
}

function updateFilterButtons() {
    const btnAll   = document.getElementById('btn-filter-all');
    const btnDrink = document.getElementById('btn-filter-drink');
    const btnFood  = document.getElementById('btn-filter-food');

    const buttons = [btnAll, btnDrink, btnFood];
    buttons.forEach(btn => {
        if (!btn) return;
        btn.classList.remove('btn-coffee', 'text-white');
        btn.classList.add('btn-outline-coffee');
    });

    let activeBtn = null;
    if (currentCategoryFilter === 'ALL') activeBtn = btnAll;
    if (currentCategoryFilter === '음료') activeBtn = btnDrink;
    if (currentCategoryFilter === '푸드') activeBtn = btnFood;

    if (activeBtn) {
        activeBtn.classList.remove('btn-outline-coffee');
        activeBtn.classList.add('btn-coffee', 'text-white');
    }
}

// ===== 상품 추가/수정/삭제 =====
function showAddProductModal() {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 추가는 사장 계정만 가능합니다.');
        return;
    }

    document.getElementById('modalTitle').textContent = '상품 추가';
    document.getElementById('productForm').reset();
    document.getElementById('productId').value = '';
    document.getElementById('productStock').value = '1';
    document.getElementById('productCategory').value = '음료';

    productModal.show();
}

async function editProduct(id) {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 수정은 사장 계정만 가능합니다.');
        return;
    }

    try {
        const response = await fetch(`/api/products/${id}`);
        if (!response.ok) throw new Error("상품 조회 실패");

        const product = await response.json();

        document.getElementById('productId').value = product.id;
        document.getElementById('productName').value = product.name;
        document.getElementById('productPrice').value = product.price;
        document.getElementById('productStock').value = product.stock > 0 ? '1' : '0';
        document.getElementById('productCategory').value = product.category;

        productModal.show();
    } catch (e) {
        alert('상품 정보를 불러오지 못했습니다.');
    }
}

async function saveProduct() {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 저장은 사장 계정만 가능합니다.');
        return;
    }

    const id = document.getElementById('productId').value;
    const product = {
        name: document.getElementById('productName').value,
        price: parseFloat(document.getElementById('productPrice').value),
        stock: parseInt(document.getElementById('productStock').value),
        category: document.getElementById('productCategory').value
    };

    try {
        const response = await fetch(id ? `/api/products/${id}` : '/api/products', {
            method: id ? 'PUT' : 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(product)
        });

        if (!response.ok) throw new Error('저장 실패');

        productModal.hide();
        await loadProducts();
        alert('저장되었습니다.');
    } catch (e) {
        alert('저장에 실패했습니다.');
    }
}

async function deleteProduct(id) {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 삭제는 사장 계정만 가능합니다.');
        return;
    }

    if (!confirm('정말 삭제하시겠습니까?')) return;

    try {
        const response = await fetch(`/api/products/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error();

        await loadProducts();
        alert('삭제되었습니다.');
    } catch {
        alert('삭제에 실패했습니다.');
    }
}

// ===== 상품 상세 보기 =====
function showProductDetail(id) {
    const product = allProducts.find(p => p.id === id);
    if (!product) {
        alert('상품 정보를 찾을 수 없습니다.');
        return;
    }

    const safe = (v) => (v === null || v === undefined || v === '' ? '-' : v);

    const img = document.getElementById('detailImage');
    if (img) {
        img.src = product.imageUrl || product.image_url || '';
    }

    document.getElementById('detailName').textContent        = safe(product.name);
    document.getElementById('detailSize').textContent        = safe(product.size);
    document.getElementById('detailCalorie').textContent     = safe(product.calorie);
    document.getElementById('detailDescription').textContent = safe(product.description);
    document.getElementById('detailAllergy').textContent     = safe(product.allergy);

    document.getElementById('detailFat').textContent      = safe(product.fat);
    document.getElementById('detailSugar').textContent    = safe(product.sugar);
    document.getElementById('detailSodium').textContent   = safe(product.sodium);
    document.getElementById('detailProtein').textContent  = safe(product.protein);
    document.getElementById('detailCaffeine').textContent = safe(product.caffeine);

    productDetailModal.show();
}

// ===== 장바구니 / 즐겨찾기 =====
async function addToCart(productId) {
    if (currentRole !== 'ROLE_USER') {
        alert('장바구니 기능은 일반 사용자 계정에서만 사용할 수 있습니다.');
        return;
    }

    try {
        const userId = currentUserId ?? 1; // 임시 fallback

        const response = await fetch('/api/cart/items', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': String(userId)   // Cart 서비스용
            },
            body: JSON.stringify({
                productId,
                quantity: 1
            })
        });

        // if (!response.ok) {
        //     const text = await response.text().catch(() => '');
        //     throw new Error(`장바구니 담기에 실패했습니다. (${response.status}) ${text}`);
        // }

        if (!response.ok) throw new Error('장바구니 담기 실패');
        alert('장바구니에 추가되었습니다.');
        //location.href = '/cart';



    } catch (error) {
        console.error('장바구니 담기에 실패했습니다:', error);
        alert('장바구니 담기에 실패했습니다.');
    }
}

async function addToFavorites(productId, productName, category, price) {
    if (currentRole !== 'ROLE_USER') {
        alert('즐겨찾기 기능은 일반 사용자 계정만 가능합니다.');
        return;
    }

    try {
        const userId = currentUserId ?? 1;

        const response = await fetch('/api/bookmarks', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId,
                productId,
                productName,
                category,
                price
            })
        });

        if (!response.ok) throw new Error();

        alert('즐겨찾기에 추가되었습니다.');
    } catch {
        alert('즐겨찾기 추가 실패!');
    }
}
