import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Join from "./pages/Join";
import Home from "./pages/Home";
import BlogPostDetail from "./pages/BlogPostDetail";
import BlogPostForm from "./pages/BlogPostForm";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/join" element={<Join />} />
        <Route path="/home" element={<Home />} />
        <Route path="/post/:id" element={<BlogPostDetail />} />
        <Route path="/create" element={<BlogPostForm />} />
        <Route path="/edit/:id" element={<BlogPostForm />} />
        <Route path="/" element={<Login />} /> {/* 기본 경로는 /login 페이지로 이동 */}
      </Routes>
    </Router>
  );
}

export default App;
