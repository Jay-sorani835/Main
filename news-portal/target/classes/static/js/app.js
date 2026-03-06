const API_BASE = '/api/news';
let currentPage = 0;
let totalPages = 1;
let currentSort = 'publishedDate,DESC';
let currentCategory = '';

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    fetchArticles();

    // Search input listener with debounce
    let timeout = null;
    document.getElementById('searchInput').addEventListener('input', (e) => {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            if (e.target.value) {
                searchArticles(e.target.value);
            } else {
                fetchArticles();
            }
        }, 500);
    });
});

// Fetch Articles
async function fetchArticles() {
    showLoader();
    try {
        const authSort = document.getElementById('sortSelect').value;
        const [sortBy, direction] = authSort.split(',');
        
        let url = `${API_BASE}?page=${currentPage}&size=6&sortBy=${sortBy}&direction=${direction}`;
        if (currentCategory) {
            url += `&category=${currentCategory}`;
        }

        const res = await fetch(url);
        const data = await res.json();
        
        totalPages = data.totalPages || 1;
        updatePaginationUI(data);
        renderArticles(data.content);
    } catch (err) {
        console.error("Failed to load articles", err);
    } finally {
        hideLoader();
    }
}

// Search
async function searchArticles(keyword) {
    showLoader();
    try {
        const res = await fetch(`${API_BASE}/search?keyword=${keyword}`);
        const data = await res.json();
        renderArticles(data);
        // Hide pagination when searching
        document.getElementById('pagination').style.display = 'none';
    } catch (err) {
        console.error("Search failed", err);
    } finally {
        hideLoader();
    }
}

// Render Data to Grid
function renderArticles(articles) {
    const grid = document.getElementById('newsGrid');
    grid.innerHTML = '';
    
    if(!articles || articles.length === 0) {
        grid.innerHTML = `<div style="grid-column: 1/-1; text-align:center; padding: 2rem; color: #6B7280;">No articles found.</div>`;
        return;
    }

    // Default image if none exists
    const placeholderImg = 'https://via.placeholder.com/400x200?text=No+Image';

    articles.forEach(article => {
        const imgUrl = article.imageUrl ? `${API_BASE}/images/${article.imageUrl}` : placeholderImg;
        const dateStr = new Date(article.publishedDate).toLocaleDateString();

        const card = document.createElement('div');
        card.className = 'news-card';
        card.innerHTML = `
            <img src="${imgUrl}" alt="News Image" class="card-img" onerror="this.src='${placeholderImg}'">
            <div class="card-content">
                <span class="card-category">${article.category || 'News'}</span>
                <h3 class="card-title">${article.title}</h3>
                <p class="card-excerpt">${article.content.substring(0, 100)}...</p>
                <div class="card-meta">
                    <span><i class="fa-regular fa-clock"></i> ${dateStr}</span>
                    <span><i class="fa-regular fa-user"></i> ${article.author}</span>
                </div>
                <div class="card-actions">
                    <button class="btn-danger-outline" onclick="deleteArticle('${article.id}')">
                        <i class="fa-solid fa-trash"></i> Delete
                    </button>
                    <button class="btn-danger-outline" style="border-color: #4F46E5; color: #4F46E5;" onclick="editArticle('${article.id}')">
                         <i class="fa-solid fa-pen"></i> Edit
                    </button>
                </div>
            </div>
        `;
        grid.appendChild(card);
    });
}

// Form Submission
async function submitArticle(e) {
    e.preventDefault();
    const id = document.getElementById('articleId').value;
    const title = document.getElementById('title').value;
    const author = document.getElementById('author').value;
    const category = document.getElementById('category').value;
    const content = document.getElementById('content').value;
    const imageFile = document.getElementById('imageFile').files[0];

    const articleData = { title, author, category, content };
    
    // Create Multipart Form Data
    const formData = new FormData();
    formData.append("article", new Blob([JSON.stringify(articleData)], { type: "application/json" }));
    if(imageFile) {
        formData.append("image", imageFile);
    }

    try {
        const btn = document.getElementById('submitBtn');
        btn.textContent = 'Publishing...';
        btn.disabled = true;

        let res;
        if(id) {
            // Update
            res = await fetch(`${API_BASE}/${id}`, {
                method: 'PUT',
                body: formData
            });
        } else {
            // Create
            res = await fetch(API_BASE, {
                method: 'POST',
                body: formData
            });
        }

        if(res.ok) {
            closeModal('createModal');
            fetchArticles();
        } else {
            alert("Failed to save article.");
        }
    } catch(err) {
        console.error(err);
        alert("An error occurred.");
    } finally {
        const btn = document.getElementById('submitBtn');
        btn.textContent = 'Publish Article';
        btn.disabled = false;
    }
}

// Edit Flow
async function editArticle(id) {
    showLoader();
    try {
        const res = await fetch(`${API_BASE}/${id}`);
        const article = await res.json();
        
        document.getElementById('articleId').value = article.id;
        document.getElementById('title').value = article.title;
        document.getElementById('author').value = article.author;
        document.getElementById('category').value = article.category;
        document.getElementById('content').value = article.content;
        
        document.getElementById('modalTitle').textContent = 'Edit Article';
        openModal('createModal');
    } catch(err) {
        console.error(err);
    } finally {
        hideLoader();
    }
}

// Delete
async function deleteArticle(id) {
    if(confirm("Are you sure you want to delete this article?")) {
        try {
            const res = await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
            if(res.ok) {
                fetchArticles();
            }
        } catch(err) {
            console.error(err);
        }
    }
}

// UI Interactions
function filterCategory(cat) {
    currentCategory = cat;
    currentPage = 0; // Reset pagination
    
    // Update active state in sidebar
    document.querySelectorAll('.category-item').forEach(el => el.classList.remove('active'));
    event.currentTarget.classList.add('active');
    
    // Re-show pagination 
    document.getElementById('pagination').style.display = 'flex';
    document.getElementById('searchInput').value = '';
    
    fetchArticles();
}

function changePage(direction) {
    const newPage = currentPage + direction;
    if(newPage >= 0 && newPage < totalPages) {
        currentPage = newPage;
        fetchArticles();
    }
}

function updatePaginationUI(data) {
    document.getElementById('pageInfo').textContent = `Page ${data.number + 1} of ${data.totalPages}`;
    document.getElementById('prevPage').disabled = data.first;
    document.getElementById('nextPage').disabled = data.last;
}

// Modal logic
function openModal(id) { 
    if(!document.getElementById('articleId').value) {
        document.getElementById('modalTitle').textContent = 'Create New Article';
        document.getElementById('articleForm').reset();
        document.getElementById('fileMessage').textContent = 'or drag and drop files here';
    }
    document.getElementById(id).style.display = 'flex'; 
}
function closeModal(id) { 
    document.getElementById(id).style.display = 'none'; 
    document.getElementById('articleForm').reset();
    document.getElementById('articleId').value = '';
}
function updateFileName() {
    const fileInput = document.getElementById('imageFile');
    const msg = document.getElementById('fileMessage');
    if(fileInput.files.length > 0) {
        msg.textContent = fileInput.files[0].name;
    }
}

function showLoader() { document.getElementById('loader').style.display = 'flex'; }
function hideLoader() { document.getElementById('loader').style.display = 'none'; }
