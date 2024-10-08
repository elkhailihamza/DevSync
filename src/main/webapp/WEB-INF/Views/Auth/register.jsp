<div class="h-screen w-screen flex justify-center items-center items-center">
    <div class="bg-white mx-4 p-8 w-96">
        <h1 class="text-3xl font-bold mb-10 text-center">Join The Community!</h1>
        <form action="${pageContext.request.contextPath}/auth/register" method="post">
            <div class="mb-4">
                <label class="block font-semibold text-gray-700 mb-2">
                    <span>Username</span>
                    <input
                            class="border-gray-300 transition-all hover:border-blue-500 rounded-sm w-full py-3 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            name="username" type="text" placeholder="Enter your username" autocomplete="off" required/>
                </label>
            </div>
            <div class="mb-4">
                <label class="block font-semibold text-gray-700 mb-2">
                    <span>Email Address</span>
                    <input class="border-gray-300 transition-all hover:border-blue-500 rounded-sm w-full py-3 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                           name="email" type="text" placeholder="example@web.com" autocomplete="off" required/>
                </label>
            </div>
            <div class="mb-4">
                <label class="block font-semibold text-gray-700 mb-2">
                    <span>Password</span>
                    <input
                            class="border-gray-300 transition-all hover:border-blue-500 rounded-sm w-full py-3 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
                            name="password" type="password" placeholder="Enter your password" autocomplete="off"  required/>
                </label>
            </div>
            <div class="mb-4 flex justify-between items-center">
                <label class="block font-semibold text-gray-700 mb-2">
                    <span>Manager</span>
                    <input type="checkbox" name="isManager" value="true" />
                </label>
            </div>
            <div class="mb-4">
                <span>Already have an account? <a href="${pageContext.request.contextPath}/auth/login" class="text-blue-600 hover:underline">login</a></span>
            </div>
            <div class="mb-6 text-center">
                <input hidden name="_type" value="register">
                <button
                        class="bg-blue-600 transition-all hover:bg-blue-700 text-white font-bold py-2 px-6 rounded focus:outline-none focus:shadow-outline"
                        type="submit">
                    Register
                </button>
            </div>
        </form>
    </div>
</div>