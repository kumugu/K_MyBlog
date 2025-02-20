import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // 페이지 이동을 위한 useNavigate 사용
import api from "../api/api";  
import "../css/Join.css";

const Join = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const navigate = useNavigate(); // 페이지 이동을 위한 navigate 함수

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await api.post("users/join", {
        username,
        password,
        email,
      });

      alert("회원가입 성공");
      console.log(response.data);
      navigate("/login");
    } catch (error) {
      alert("회원가입 실패");
      console.error(error);
    }
  };

  return (
    <div className="join-container">
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label>아이디:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            placeholder="아이디를 입력하세요"
          />
        </div>
        <div className="input-group">
          <label>비밀번호:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            placeholder="비밀번호를 입력하세요"
          />
        </div>
        <div className="input-group">
          <label>이메일:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            placeholder="이메일을 입력하세요"
          />
        </div>
        <button type="submit">가입하기</button>
      </form>
    </div>
  );
};


export default Join;
