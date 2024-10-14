const tags = [];
const input = document.getElementById("tagInput");
const addTagsBtn = document.getElementById("addTagsBtn");
const tagError = document.getElementById("tagError");
const tagContainer = document.getElementById("tagContainer");

const checkIfTagsExist = () => {
    if (tagList) {
        tagList.forEach(createTag);
    }
}

// Load existing tags when the page loads
window.addEventListener("load", checkIfTagsExist);

// Create and display a tag
const createTag = (tagValue) => {
    const value = tagValue.trim();

    if (tags.length >= 5) {
        return addTagError("You can only create 5 tags!");
    }

    if (!value) {
        return addTagError("No value provided!");
    }

    if (tags.includes(value)) {
        return addTagError("Value already exists!");
    }

    emptyTagError();
    displayTag(value);
    tags.push(value);

    input.value = "";
    input.focus();
}

// Display tag element
const displayTag = (value) => {
    const tag = document.createElement('span');
    tag.classList.add(
        "tag", "rounded-md", "text-sm", "bold", "py-1", "px-2",
        "flex", "gap-2", "justify-between", "items-center"
    );

    const styles = {
        backgroundColor: '#d8d8d8', borderRadius: '0.375rem', padding: '0.25rem 0.5rem',
        display: 'inline-flex', alignItems: 'center', gap: '0.5rem',
        justifyContent: 'space-between', fontSize: '0.875rem', fontWeight: 'bold',
    };

    Object.assign(tag.style, styles);

    tag.textContent = value;
    tag.appendChild(createRemoveBtn(value));
    tagContainer.appendChild(tag);
}

// Create remove button for tags
const createRemoveBtn = (value) => {
    const close = document.createElement('span');
    close.classList.add("remove-tag", "cursor-pointer");
    close.innerHTML = xBtnForRemoval();

    close.onclick = () => {
        tagContainer.removeChild(close.parentNode);
        removeTag(value);
    };

    return close;
}

// Remove tag from the tags array
const removeTag = (value) => {
    const index = tags.indexOf(value);
    if (index > -1) {
        tags.splice(index, 1);
    }
}

// SVG for close button
const xBtnForRemoval = () => {
    return `
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
            stroke="#000000" fill="none" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <line x1="18" y1="6" x2="6" y2="18"></line>
            <line x1="6" y1="6" x2="18" y2="18"></line>
        </svg>
    `;
}

// Event listeners
addTagsBtn.addEventListener("click", () => createTag(input.value));

// Prepare tags for submission
const prepareTagsForSubmission = () => {
    tagContainer.innerHTML = "";

    tags.forEach(tag => {
        const hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'tags[]';
        hiddenInput.value = tag;
        tagContainer.appendChild(hiddenInput);
    });
}

// Error handling
const addTagError = (message) => {
    tagError.innerHTML = message;
    tagError.classList.remove("hidden");
}

const emptyTagError = () => {
    tagError.innerHTML = "";
    tagError.classList.add("hidden");
}