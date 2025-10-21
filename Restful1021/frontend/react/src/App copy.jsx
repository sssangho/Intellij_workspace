import { useState,useEffect } from 'react'

const API_URL = 'http://localhost:8926/api/users';

function App() {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState({ id: '', username: '', password: '' });

  // 사용자 목록 조회
  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const res = await fetch(API_URL);
      const data = await res.json();
      setUsers(data);
    } catch (err) {
      alert('사용자 목록을 불러오는 데 실패했습니다.');
    }
  };

  // 입력 값 변경 핸들러
  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({ ...formData, [id]: value });
  };

  // 사용자 생성 또는 수정
  const createOrUpdateUser = async () => {
    const { id, username, password } = formData;

    if (!username.trim() || !password.trim()) {
      alert('이름과 암호를 입력하세요.');
      return;
    }

    const user = {
      username: username.trim(),
      password: password.trim()
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_URL}/${id}` : API_URL;

    try {
      const res = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
      });

      if (!res.ok) throw new Error('요청 실패');

      await res.json();
      fetchUsers();
      clearForm();
    } catch (err) {
      alert('에러 발생: ' + err.message);
    }
  };

  // 사용자 삭제
  const deleteUser = async (id) => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return;

    try {
      await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
      fetchUsers();
    } catch (err) {
      alert('삭제 실패: ' + err.message);
    }
  };

  // 사용자 정보 편집
  const editUser = (user) => {
    setFormData({
      id: user.id,
      username: user.username,
      password: user.password
    });
  };

  // 폼 초기화
  const clearForm = () => {
    setFormData({ id: '', username: '', password: '' });
  };

  return (
    <div style={{ margin: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>User CRUD UI</h1>

      {/* Form */}
      <div>
        <input
          type="hidden"
          id="id"
          value={formData.id}
        />
        <input
          type="text"
          id="username"
          placeholder="이름"
          value={formData.username}
          onChange={handleChange}
        />
        <input
          type="text"
          id="password"
          placeholder="암호"
          value={formData.password}
          onChange={handleChange}
        />
        <button onClick={createOrUpdateUser}>저장</button>
        <button onClick={clearForm}>취소</button>
      </div>

      {/* User Table */}
      <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>이름</th>
            <th>암호</th>
            <th>동작</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.username}</td>
              <td>{user.password}</td>
              <td>
                <button onClick={() => editUser(user)}>수정</button>
                <button onClick={() => deleteUser(user.id)}>삭제</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
