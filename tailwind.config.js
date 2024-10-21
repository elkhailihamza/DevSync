/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './main/webapp/css/*.{html,js,ts,jsx,tsx,jsp}',
    './main/webapp/js/*.{html,js,ts,jsx,tsx,jsp}',
    './main/webapp/WEB-INF/*.{html,js,ts,jsx,tsx,jsp}',
    './main/webapp/WEB-INF/Views/*.{html,js,ts,jsx,tsx,jsp}',
    './main/webapp/WEB-INF/Views/**/*.{html,js,ts,jsx,tsx,jsp}',
    './main/webapp/WEB-INF/Views/Task/TaskList.jsp',
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

