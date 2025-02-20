import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";
import '../css/Login.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const encodedUsername = encodeURIComponent(username);
    const encodedPassword = encodeURIComponent(password);

    try {
      const response = await api.post('users/login', { username: encodedUsername, password: encodedPassword });

      if (response.status === 200) {
        alert(response.data);
        navigate("/home");
      }
    } catch (error) {
      alert("로그인 실패: 아이디와 비밀번호를 확인하세요.");
      console.error("Login error:", error);
    }
  };

  const handleSignupRedirect = () => {
    navigate("/join");
  };

  return (
    <div className="auth-page">
      <div className="auth-container">
        <h2 className="auth-title">로그인</h2>
        <form onSubmit={handleSubmit} className="auth-form">
          <div className="auth-input-group">
            <label htmlFor="username">아이디:</label>
            <input
              id="username"
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              autoComplete="username"
              className="auth-input"
            />
          </div>
          <div className="auth-input-group">
            <label htmlFor="password">비밀번호:</label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              autoComplete="current-password"
              className="auth-input"
            />
          </div>
          <button type="submit" className="auth-submit-btn">로그인</button>
        </form>

        <div className="auth-prompt">
          <p>아이디가 없으신가요?</p>
          <button onClick={handleSignupRedirect} className="auth-secondary-btn">회원가입</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
