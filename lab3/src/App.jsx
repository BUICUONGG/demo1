import React, { useState, useEffect } from "react";
import { Pencil, Trash2, Plus } from "lucide-react";
import { Alert } from "@/components/ui/alert";
import { toast, Toaster } from "react-hot-toast"; // Import thêm Toaster

const App = () => {
  const [todos, setTodos] = useState(() => {
    // lưu mảng todo
    const savedTodos = localStorage.getItem("todos");
    return savedTodos ? JSON.parse(savedTodos) : [];
  });
  const [inputValue, setInputValue] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editValue, setEditValue] = useState("");
  const [error, setError] = useState("");
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [todoToDelete, setTodoToDelete] = useState(null);

  useEffect(() => {
    localStorage.setItem("todos", JSON.stringify(todos));
  }, [todos]);
  console.log(localStorage.getItem("todos"));

  const handleSubmit = (e) => {
    e.preventDefault();
    const trimmedValue = inputValue.trim();

    if (!trimmedValue) {
      setError("Todo cannot be empty");
      return;
    }

    if (trimmedValue.length > 200) {
      setError("Todo must be less than 200 characters");
      return;
    }

    if (
      todos.some(
        (todo) => todo.text.toLowerCase() === trimmedValue.toLowerCase()
      )
    ) {
      setError("This todo already exists");
      return;
    }

    const newTodo = {
      id: Date.now(),
      text: trimmedValue,
      completed: false,
    };

    setTodos([...todos, newTodo]);
    setInputValue("");
    setError("");
    toast.success("Todo added successfully!"); // Add success toast for adding
  };

  const toggleComplete = (id) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  };

  const startEdit = (todo) => {
    setEditingId(todo.id);
    setEditValue(todo.text);
    setError("");
  };

  const handleEdit = (id) => {
    const trimmedValue = editValue.trim();

    if (!trimmedValue) {
      setError("Todo cannot be empty");
      return;
    }

    if (trimmedValue.length > 200) {
      setError("Todo must be less than 200 characters");
      return;
    }

    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, text: trimmedValue } : todo
      )
    );
    setEditingId(null);
    setEditValue("");
    setError("");
    toast.success("Todo updated successfully!"); // Add success toast for editing
  };

  const confirmDelete = (id) => {
    setTodoToDelete(id);
    setShowDeleteModal(true);
  };

  const handleDelete = () => {
    setTodos(todos.filter((todo) => todo.id !== todoToDelete));
    setShowDeleteModal(false);
    setTodoToDelete(null);
    toast.success("Todo deleted successfully!"); // Add success toast for deletion
  };

  return (
    <div className="min-h-screen bg-white">
      {/* Thêm Toaster component vào đây */}
      <Toaster position="top-right" />

      <div className="container mx-auto px-4 py-8 max-w-2xl">
        <div className="mb-8 text-center">
          <h1 className="text-4xl font-bold text-gray-800">Todo List</h1>
        </div>

        {error && <Alert>{error}</Alert>}

        <div className="mb-6">
          <form
            onSubmit={handleSubmit}
            className="flex gap-4"
            data-testid="todo-form"
          >
            <input
              type="text"
              value={inputValue}
              onChange={(e) => setInputValue(e.target.value)}
              placeholder="Add new todo..."
              className="flex-1 px-6 py-4 text-lg border border-gray-200 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-100"
              maxLength={200}
              data-testid="todo-input"
            />
            <button
              type="submit"
              className="px-6 py-4 bg-blue-500 text-white rounded-lg shadow-md flex items-center gap-2 hover:bg-blue-600 transition-colors"
              data-testid="add-button"
            >
              <Plus />
              <span>Add</span>
            </button>
          </form>
        </div>

        <div data-testid="todo-list" className="space-y-2">
          {todos.length === 0 && (
            <p className="text-center text-gray-500 py-4">
              No todos yet. Start adding!
            </p>
          )}
          {todos.map((todo) => (
            <div
              key={todo.id}
              className="flex items-center p-4 bg-gray-50 rounded-md border-l-4 border-transparent"
            >
              <div className="mr-3">
                <input
                  type="checkbox"
                  checked={todo.completed}
                  onChange={() => toggleComplete(todo.id)}
                  className="h-6 w-6 rounded border-gray-300 text-blue-500 focus:ring-blue-500"
                  data-testid={`checkbox-${todo.id}`}
                />
              </div>
              {editingId === todo.id ? (
                <>
                  <input
                    type="text"
                    value={editValue}
                    onChange={(e) => setEditValue(e.target.value)}
                    className="flex-1 px-2 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-100"
                  />
                  <button
                    onClick={() => handleEdit(todo.id)}
                    className="ml-2 text-blue-500"
                    data-testid={`save-button-${todo.id}`}
                  >
                    Save
                  </button>
                </>
              ) : (
                <span
                  className={`flex-1 text-lg ${
                    todo.completed
                      ? "line-through text-gray-400"
                      : "text-gray-700"
                  }`}
                >
                  {todo.text}
                </span>
              )}
              <div className="flex space-x-2">
                <button
                  onClick={() => startEdit(todo)}
                  className="text-blue-500 p-2 hover:bg-blue-50 rounded-full"
                  data-testid={`edit-button-${todo.id}`}
                >
                  <Pencil className="h-5 w-5" />
                </button>
                <button
                  onClick={() => confirmDelete(todo.id)}
                  className="text-red-500 p-2 hover:bg-red-50 rounded-full"
                  data-testid={`delete-button-${todo.id}`}
                >
                  <Trash2 className="h-5 w-5" />
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      {showDeleteModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <p>Are you sure you want to delete this todo?</p>
            <div className="flex justify-end gap-4 mt-4">
              <button
                onClick={() => setShowDeleteModal(false)}
                className="px-4 py-2 bg-gray-300 rounded-lg"
              >
                Cancel
              </button>
              <button
                onClick={handleDelete}
                className="px-4 py-2 bg-red-500 text-white rounded-lg"
              >
                Delete
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default App;
