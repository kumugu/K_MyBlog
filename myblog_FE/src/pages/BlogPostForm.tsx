import React, { useEffect, useState, FormEvent } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../api/api";
import "../css/BlogPostForm.css"; // 스타일을 적용할 CSS 파일

// BlogPost 데이터의 타입을 정의
interface BlogPost {
  title: string;
  content: string;
  author: string;
}

const BlogPostForm: React.FC = () => {
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [author, setAuthor] = useState<string>("");
  const { id } = useParams<{ id: string }>(); // id는 문자열로 타입을 정의
  const navigate = useNavigate();

  // Edit 모드일 때 기존 포스트 정보를 불러오는 effect
  useEffect(() => {
    if (id) {
      const fetchPost = async () => {
        try {
          const response = await api.get(`/blogposts/${id}`);
          setTitle(response.data.title);
          setContent(response.data.content);
          setAuthor(response.data.author);
        } catch (error) {
          console.error("Error fetching post:", error);
        }
      };
      fetchPost();
    }
  }, [id]);

  // 폼 제출 처리 함수
  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const postData: BlogPost = { title, content, author };

    try {
      if (id) {
        await api.put(`/blogposts/${id}`, postData);
      } else {
        await api.post("/blogposts", postData);
      }
      navigate("/home");
    } catch (error) {
      console.error("Error saving post:", error);
    }
  };

  return (
    <div className="blogpost-form-container">
      <h1>{id ? "Edit Post" : "Create New Post"}</h1>
      <form onSubmit={handleSubmit} className="blogpost-form">
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Title"
          className="form-input"
          required
        />
        <textarea
          value={content}
          onChange={(e) => setContent(e.target.value)}
          placeholder="Content"
          className="form-textarea"
          required
        />
        <input
          type="text"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
          placeholder="Author"
          className="form-input"
          required
        />
        <button type="submit" className="form-button">
          {id ? "Update Post" : "Create Post"}
        </button>
      </form>
    </div>
  );
};

export default BlogPostForm;
