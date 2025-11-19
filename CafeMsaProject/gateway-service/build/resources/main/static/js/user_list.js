// /js/user_list.js

// ===== 로그인한 유저 ID 전역 =====
let CURRENT_USER_ID = null;

// 공통 헤더 생성 함수
function buildHeaders(extra = {}) {
    if (!CURRENT_USER_ID) {
        alert('로그인이 필요합니다.');
        location.href = '/login';
        throw new Error('로그인 필요');  // fetch 진행 막기용
    }

    return {
        'X-USER-ID': CURRENT_USER_ID,
        'Accept': 'application/json',
        ...extra,
    };
}

// 금액 포맷팅 함수
function money(n) {
    return (Number(n) || 0).toLocaleString() + '원';
}

// 상태 배지 생성 함수
function badge(status) {
    let className = 'bg-secondary';
    switch (String(status).toUpperCase()) {
        case 'NEW':
        case 'PAID':
            className = 'bg-primary';
            break;
        case 'COMPLETED':
            className = 'bg-success';
            break;
        case 'CANCELLED':
        case 'FAILED':
            className = 'bg-danger';
            break;
    }
    return `<span class="badge status-badge ${className}">${status}</span>`;
}

// ===== 주문 목록 로딩 =====
async function loadList() {
    const loading = document.getElementById('loading');
    const empty   = document.getElementById('empty');
    const tbody   = document.getElementById('tbody');
    const tableCard = document.getElementById('orderTableCard');

    // 로딩 표시
    if (loading) loading.classList.remove('d-none');
    if (empty)   empty.classList.add('d-none');
    if (tbody)   tbody.innerHTML = '';
    if (tableCard) tableCard.classList.remove('d-none');

    try {
        const res = await fetch('/api/orders', { headers: buildHeaders() });
        if (!res.ok) throw new Error('주문 목록 조회 실패');

        const list = await res.json();
        console.log('받은 데이터:', list);

        if (!list || !list.length) {
            // 비어 있을 때
            if (loading) loading.classList.add('d-none');
            if (empty)   empty.classList.remove('d-none');
            if (tableCard) tableCard.classList.add('d-none');
            return;
        }

        for (const o of list) {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td><a href="/orders/${o.id}">#${o.id}</a></td>
                <td>${badge(o.status)}</td>
                <td class="text-end">${o.totalQuantity}</td>
                <td class="text-end fw-semibold">${money(o.totalAmount)}</td>
                <td class="text-end">
                    <a class="btn btn-sm btn-outline-secondary" href="/orders/${o.id}">상세보기</a>
                </td>
            `;
            tbody.appendChild(tr);
        }
        if (tableCard) tableCard.classList.remove('d-none');
    } catch (e) {
        console.error('주문 목록 불러오기 오류:', e);
        alert('주문 목록을 불러오는 중 오류가 발생했습니다.');
    } finally {
        if (loading) loading.classList.add('d-none');
    }
}

// ===== 초기 진입 =====
document.addEventListener('DOMContentLoaded', async () => {
    // products.js / user_cart.js와 동일한 로그인 체크
    const token    = localStorage.getItem('token');
    const username = localStorage.getItem('username');

    // 로그인 여부는 token + username 기준으로만 판단
    if (!token || !username) {
        alert('로그인이 필요합니다.');
        location.href = '/login';
        return;
    }

    // userId 는 있으면 쓰고, 없으면 예전 키/임시값으로 fallback
    const userIdFromNew   = localStorage.getItem('userId');
    const userIdFromLocal = localStorage.getItem('USER_ID');
    const userIdFromSess  = sessionStorage.getItem('USER_ID');

    CURRENT_USER_ID = String(
        userIdFromNew ||
        userIdFromLocal ||
        userIdFromSess ||
        '1' // 마지막 임시값 (테스트용)
    );

    console.debug('[orders list] token=', token,
        'username=', username,
        'X-USER-ID=', CURRENT_USER_ID);

    await loadList().catch(console.error);
});
