import { useState,useEffect } from 'react'

const API_URL = 'http://localhost:8937/api/users';

function App() {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState({ id: '', username: '', password: '' });

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

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({ ...formData, [id]: value });
  };

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

  const deleteUser = async (id) => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return;

    try {
      await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
      fetchUsers();
    } catch (err) {
      alert('삭제 실패: ' + err.message);
    }
  };

  const editUser = (user) => {
    setFormData({
      id: user.id,
      username: user.username,
      password: user.password
    });
  };

  const clearForm = () => {
    setFormData({ id: '', username: '', password: '' });
  };

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>👤 User CRUD UI</h1>

      <div style={styles.form}>
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
          style={styles.input}
        />
        <input
          type="text"
          id="password"
          placeholder="암호"
          value={formData.password}
          onChange={handleChange}
          style={styles.input}
        />
        <button onClick={createOrUpdateUser} style={{ ...styles.button, backgroundColor: '#4CAF50' }}>
          저장
        </button>
        <button onClick={clearForm} style={{ ...styles.button, backgroundColor: '#f44336' }}>
          취소
        </button>
      </div>

      <table style={styles.table}>
        <thead>
          <tr>
            <th style={styles.th}>ID</th>
            <th style={styles.th}>이름</th>
            <th style={styles.th}>암호</th>
            <th style={styles.th}>동작</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id} style={styles.tr}>
              <td style={styles.td}>{user.id}</td>
              <td style={styles.td}>{user.username}</td>
              <td style={styles.td}>{user.password}</td>
              <td style={styles.td}>
                <button onClick={() => editUser(user)} style={{ ...styles.buttonSmall, backgroundColor: '#2196F3' }}>
                  수정
                </button>
                <button onClick={() => deleteUser(user.id)} style={{ ...styles.buttonSmall, backgroundColor: '#e91e63' }}>
                  삭제
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

const styles = {
  container: {
    fontFamily: 'Arial, sans-serif',
    padding: '40px',
    maxWidth: '800px',
    margin: '0 auto'
  },
  title: {
    textAlign: 'center',
    marginBottom: '30px'
  },
  form: {
    display: 'flex',
    gap: '10px',
    marginBottom: '30px',
    flexWrap: 'wrap'
  },
  input: {
    padding: '10px',
    borderRadius: '4px',
    border: '1px solid #ccc',
    flex: '1 0 200px'
  },
  button: {
    padding: '10px 20px',
    border: 'none',
    color: 'white',
    borderRadius: '4px',
    cursor: 'pointer'
  },
  buttonSmall: {
    padding: '6px 12px',
    marginRight: '5px',
    border: 'none',
    color: 'white',
    borderRadius: '4px',
    cursor: 'pointer'
  },
  table: {
    width: '100%',
    borderCollapse: 'collapse',
    boxShadow: '0 0 10px rgba(0,0,0,0.1)'
  },
  th: {
    backgroundColor: '#f2f2f2',
    padding: '12px',
    textAlign: 'left',
    borderBottom: '2px solid #ddd'
  },
  td: {
    padding: '12px',
    borderBottom: '1px solid #eee'
  },
  tr: {
    transition: 'background-color 0.2s'
  }
};

export default App;
